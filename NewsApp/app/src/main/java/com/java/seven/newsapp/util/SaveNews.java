package com.java.seven.newsapp.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.java.seven.newsapp.bean.LatestNews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zzy on 2017/9/11.
 */

public class SaveNews {
    public static String TAG = "SaveNews";

    public static void saveNewsBean(String filename, List<LatestNews.ListBean> beans) throws IOException {
        FileOutputStream fileOut;
        fileOut = AppGlobal.activity.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        LatestNews.ListBean[] array = new LatestNews.ListBean[beans.size()];
        beans.toArray(array);
        out.writeObject(array);
        out.close();
        fileOut.close();
    }

    public static List<LatestNews.ListBean> readNewsBean(String filename) throws IOException, ClassNotFoundException {
        File file = new File(AppGlobal.activity.getFilesDir(), filename);
        if (file.isFile()) {
            FileInputStream fileInput = AppGlobal.activity.openFileInput(filename);
            ObjectInputStream input = new ObjectInputStream(fileInput);
            LatestNews.ListBean[] array = (LatestNews.ListBean[]) input.readObject();
            List<LatestNews.ListBean> beans = Arrays.asList(array);
            input.close();
            fileInput.close();
            return beans;
        }
        else
            return new ArrayList<>();
    }
}
