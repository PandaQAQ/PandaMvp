package com.pandaq.uires.zxing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.pandaq.uires.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Initial the camera
 * <p>
 * 默认的二维码扫描Activity
 */
public class CaptureActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
        captureFragment.setCameraInitCallBack(e -> {
            if (e != null) {
                Log.e("TAG", "callBack: ", e);
            }
        });

    }

    /**
     * 二维码解析回调函数
     */
    ZxingUtils.AnalyzeCallback analyzeCallback = new ZxingUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(ZxingUtils.RESULT_TYPE, ZxingUtils.RESULT_SUCCESS);
            bundle.putString(ZxingUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(ZxingUtils.RESULT_TYPE, ZxingUtils.RESULT_FAILED);
            bundle.putString(ZxingUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }
    };
}