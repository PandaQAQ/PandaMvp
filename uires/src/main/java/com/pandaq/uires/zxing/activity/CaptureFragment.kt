package com.pandaq.uires.zxing.activity

import android.content.Context
import android.graphics.Bitmap
import android.hardware.Camera
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.pandaq.uires.R
import com.pandaq.uires.zxing.activity.ZxingUtils.AnalyzeCallback
import com.pandaq.uires.zxing.camera.CameraManager
import com.pandaq.uires.zxing.decoding.CaptureActivityHandler
import com.pandaq.uires.zxing.decoding.InactivityTimer
import com.pandaq.uires.zxing.view.ViewfinderView
import java.io.IOException
import java.util.*

/**
 * 自定义实现的扫描Fragment
 */
class CaptureFragment : Fragment(), SurfaceHolder.Callback {
    private var handler: CaptureActivityHandler? = null
    private var viewfinderView: ViewfinderView? = null
    private var hasSurface = false
    private var decodeFormats: Vector<BarcodeFormat>? = null
    private var characterSet: String? = null
    private var inactivityTimer: InactivityTimer? = null
    private var mediaPlayer: MediaPlayer? = null
    private var playBeep = false
    private var vibrate = false
    private var surfaceHolder: SurfaceHolder? = null
    var analyzeCallback: AnalyzeCallback? = null
    private var camera: Camera? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CameraManager.init(activity!!.application)
        hasSurface = false
        inactivityTimer = InactivityTimer(this.activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        var view: View? = null
        if (bundle != null) {
            val layoutId = bundle.getInt(ZxingUtils.LAYOUT_ID)
            if (layoutId != -1) {
                view = inflater.inflate(layoutId, null)
            }
        }
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_capture, null)
        }
        viewfinderView = view!!.findViewById(R.id.viewfinder_view)
        val surfaceView = view.findViewById<SurfaceView>(R.id.preview_view)
        surfaceHolder = surfaceView.holder
        return view
    }

    override fun onResume() {
        super.onResume()
        if (hasSurface) {
            initCamera(surfaceHolder)
        } else {
            surfaceHolder!!.addCallback(this)
            surfaceHolder!!.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        }
        decodeFormats = null
        characterSet = null
        playBeep = true
        val audioService = activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (audioService.ringerMode != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false
        }
        initBeepSound()
        vibrate = true
    }

    override fun onPause() {
        super.onPause()
        if (handler != null) {
            handler!!.quitSynchronously()
            handler = null
        }
        CameraManager.get().closeDriver()
    }

    override fun onDestroy() {
        super.onDestroy()
        inactivityTimer!!.shutdown()
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    fun handleDecode(result: Result?, barcode: Bitmap?) {
        inactivityTimer!!.onActivity()
        playBeepSoundAndVibrate()
        if (result == null || TextUtils.isEmpty(result.text)) {
            if (analyzeCallback != null) {
                analyzeCallback!!.onAnalyzeFailed()
            }
        } else {
            if (analyzeCallback != null) {
                analyzeCallback!!.onAnalyzeSuccess(barcode, result.text)
            }
        }
    }

    private fun initCamera(surfaceHolder: SurfaceHolder?) {
        camera = try {
            CameraManager.get().openDriver(surfaceHolder)
            CameraManager.get().camera
        } catch (e: Exception) {
            if (callBack != null) {
                callBack!!.callBack(e)
            }
            return
        }
        if (callBack != null) {
            callBack!!.callBack(null)
        }
        if (handler == null) {
            handler = CaptureActivityHandler(this, decodeFormats, characterSet, viewfinderView)
        }
    }

    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int, width: Int,
        height: Int
    ) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!hasSurface) {
            hasSurface = true
            initCamera(holder)
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        hasSurface = false
        if (camera != null) {
            if (camera != null && CameraManager.get().isPreviewing) {
                if (!CameraManager.get().isUseOneShotPreviewCallback) {
                    camera!!.setPreviewCallback(null)
                }
                camera!!.stopPreview()
                CameraManager.get().previewCallback.setHandler(null, 0)
                CameraManager.get().autoFocusCallback.setHandler(null, 0)
                CameraManager.get().isPreviewing = false
            }
        }
    }

    fun getHandler(): Handler? {
        return handler
    }

    fun drawViewfinder() {
        viewfinderView!!.drawViewfinder()
    }

    private fun initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            activity!!.volumeControlStream = AudioManager.STREAM_MUSIC
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer!!.setOnCompletionListener(beepListener)
            val file = resources.openRawResourceFd(R.raw.beep)
            try {
                mediaPlayer!!.setDataSource(
                    file.fileDescriptor,
                    file.startOffset, file.length
                )
                file.close()
                mediaPlayer!!.setVolume(BEEP_VOLUME, BEEP_VOLUME)
                mediaPlayer!!.prepare()
            } catch (e: IOException) {
                mediaPlayer = null
            }
        }
    }

    private fun playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer!!.start()
        }
        if (vibrate) {
            val vibrator = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VIBRATE_DURATION)
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private val beepListener =
        MediaPlayer.OnCompletionListener { mediaPlayer -> mediaPlayer.seekTo(0) }
    var callBack: CameraInitCallBack? = null

    /**
     * Set callback for Camera check whether Camera init success or not.
     */
    fun setCameraInitCallBack(callBack: CameraInitCallBack?) {
        this.callBack = callBack
    }

    interface CameraInitCallBack {
        /**
         * Callback for Camera init result.
         * @param e If is's null,means success.otherwise Camera init failed with the Exception.
         */
        fun callBack(e: Exception?)
    }

    companion object {
        private const val BEEP_VOLUME = 0.10f
        private const val VIBRATE_DURATION = 200L
    }
}