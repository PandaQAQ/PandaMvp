
package com.pandaq.appcore.permission.test;

import android.media.MediaRecorder;

import java.io.File;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :record audio permission tester
 */

public class RecordAudioTest extends PermissionTest {

    private File mTempFile = null;
    private MediaRecorder mRecorder = null;

    public RecordAudioTest() {
        mRecorder = new MediaRecorder();
    }

    @Override
    public boolean test() throws Throwable {
        try {
            mTempFile = File.createTempFile("permission", "test");
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mTempFile.getAbsolutePath());
            mRecorder.prepare();
            mRecorder.start();
            return true;
        } finally {
            stop();
        }
    }

    private void stop() {
        if (mRecorder != null) {
            try {
                mRecorder.stop();
            } catch (Exception ignored) {
            }
            try {
                mRecorder.release();
            } catch (Exception ignored) {
            }
        }

        if (mTempFile != null && mTempFile.exists()) {
            // noinspection ResultOfMethodCallIgnored
            mTempFile.delete();
        }
    }
}