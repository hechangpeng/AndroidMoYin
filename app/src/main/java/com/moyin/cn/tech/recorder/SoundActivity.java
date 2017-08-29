package com.moyin.cn.tech.recorder;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zph.mysound.R;

import org.fmod.FMOD;

import java.io.IOException;

public class SoundActivity extends Activity {
    private Button mRecordBtn;
    private boolean mIsRecording = false;
    private boolean isTestPlay = false;
    private RecordManager mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FMOD.init(this);
        setContentView(R.layout.activity_main);
        mRecorder = new RecordManager();
        mRecordBtn = (Button) findViewById(R.id.record_btn);
        mRecordBtn.setOnClickListener(recordClick);
    }

    private View.OnClickListener recordClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mIsRecording) {
                mRecorder.startRecord();
                mRecordBtn.setText("关闭录音");
                mIsRecording = true;
            } else {
                mRecorder.stopRecord();
                mRecordBtn.setText("启动录音");
                mIsRecording = false;
            }
        }
    };

    public void mFix(View v) {
        if (mIsRecording) {
            Toast.makeText(this, "请先关闭录音", Toast.LENGTH_SHORT).show();
            return;
        }
        String path = mRecorder.getRecordPath();
        if (isTestPlay) {
            playRecord(path);
            return;
        }
        switch (v.getId()) {
            case R.id.btn_normal:
                doChange(path, SoundMagicApi.MODE_NORMAL);
                break;
            case R.id.btn_luoli:
                doChange(path, SoundMagicApi.MODE_LUOLI);
                break;
            case R.id.btn_dashu:
                doChange(path, SoundMagicApi.MODE_DASHU);
                break;
            case R.id.btn_jingsong:
                doChange(path, SoundMagicApi.MODE_JINGSONG);
                break;
            case R.id.btn_gaoguai:
                doChange(path, SoundMagicApi.MODE_GAOGUAI);
                break;
            case R.id.btn_kongling:
                doChange(path, SoundMagicApi.MODE_KONGLING);
                break;
            default:
                break;
        }
    }

    private void doChange(final String path, final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SoundMagicApi.changeSound(path, type);
            }
        }).start();
    }

    private void playRecord(String path) {
        final MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }
}
