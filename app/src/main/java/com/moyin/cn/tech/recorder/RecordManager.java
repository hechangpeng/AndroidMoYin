package com.moyin.cn.tech.recorder;

import android.os.Environment;
import android.util.Log;

/**
 * Date: 2017/6/20
 * Author：HeChangPeng
 * Describe：录音管理器
 */

public class RecordManager {
    private ExtAudioRecorder mRecorder;
    private boolean mIsRecording = false;
    private String mPath = DEFAULT_PATH;
    private static final String DEFAULT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/magicsound.wav";

    public RecordManager() {
    }

    public void setPath(String path) {
        if (path == null || !path.endsWith(".wav")) {
            mPath = DEFAULT_PATH;
            return;
        }
        mPath = path;
    }

    public String getRecordPath() {
        return mPath;
    }

    public synchronized void startRecord() {
        if (mIsRecording) {
            Log.e("hecp", "已在录音中，无需再次录音");
            return;
        }
        if (mRecorder == null) {
            mRecorder = ExtAudioRecorder.getInstanse(false);
        }
        mRecorder.setOutputFile(mPath);
        mRecorder.prepare();
        mRecorder.start();
        mIsRecording = true;
    }

    public synchronized void stopRecord() {
        if (!mIsRecording) {
            Log.e("hecp", "录音已结束，无需再次结束");
            return;
        }
        if (mRecorder == null) {
            return;
        }
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        mIsRecording = false;
    }
}
