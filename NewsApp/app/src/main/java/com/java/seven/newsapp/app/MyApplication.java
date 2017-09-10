package com.java.seven.newsapp.app;

import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.baidu.tts.client.SpeechSynthesizer;
import com.java.seven.newsapp.util.AppConstants;
import com.java.seven.newsapp.util.SharedPreferencesUtil;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by linsawako on 2017/2/7.
 */

public class MyApplication extends android.support.multidex.MultiDexApplication {

    private String mDirPath;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        boolean isNight = SharedPreferencesUtil.getBoolean(this, AppConstants.ISNIGHT, false);
        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //不使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        initEnv();
        initTts();

    }

    public void initTts() {
        // 获取语音合成对象实例
        SpeechSynthesizer mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        // 设置在线语音合成授权，需要填入从百度语音官网申请的api_key和secret_key
        mSpeechSynthesizer.setApiKey(AppConstants.BAIDU_APIKEY, AppConstants.BAIDU_SECRET_KEY);
        // 设置离线语音合成授权，需要填入从百度语音官网申请的app_id
        mSpeechSynthesizer.setAppId(AppConstants.BAIDU_APP_ID);

        // 设置语音合成文本模型文件
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mDirPath + AppConstants.TTS_TEXT_FILE);
        // 设置语音合成声音模型文件
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mDirPath + AppConstants.TTS_SPEECH_FILE);
        // 设置语音合成声音授权文件
//        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, "your_licence_path");
    }

    private void initEnv() {
        if (mDirPath == null) {
            String sdcardPath = Environment.getExternalStorageDirectory().toString();
            mDirPath = sdcardPath + "/" + AppConstants.DIR_NAME;
        }
        makeDir(mDirPath);
        copyFromAssetsToSdcard(false, AppConstants.TTS_SPEECH_FILE, mDirPath + "/" + AppConstants.TTS_SPEECH_FILE);
        copyFromAssetsToSdcard(false, AppConstants.TTS_TEXT_FILE, mDirPath + "/" + AppConstants.TTS_TEXT_FILE);
//        copyFromAssetsToSdcard(false, LICENSE_FILE_NAME, mDirPath + "/" + LICENSE_FILE_NAME);
    }

    private void makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void copyFromAssetsToSdcard(boolean isCover, String source, String dest) {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = getResources().getAssets().open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
