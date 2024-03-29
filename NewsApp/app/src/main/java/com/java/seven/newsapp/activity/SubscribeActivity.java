package com.java.seven.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.java.seven.newsapp.R;
import com.java.seven.newsapp.chinesenews.news.NewsCategory;

/**
 * Created by caibao on 17-9-9.
 */

public class SubscribeActivity extends AppCompatActivity {
    View subscribeButton;
    public static final int RESULT_CODE = 1;
    public static final String KEY = "subscribeState";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe_layout);

        initViewRef();
        initView();
        initListener();
    }

    private void initViewRef() {
        subscribeButton = findViewById(R.id.subscribe_button);


    }

    private void initView() {
        Intent intent = getIntent();
        boolean[] subscribeStates = intent.getBooleanArrayExtra(KEY);
        for (int i = 1; i <= NewsCategory.CATEGORY_CNT; ++i) {
            View categoryItem = findViewById(NewsCategory.codeToId(i));
            TextView tv = categoryItem.findViewById(R.id.category_name);
            CheckBox cb = categoryItem.findViewById(R.id.checkBox);
            tv.setText(NewsCategory.codeToName(i));
            cb.setChecked(subscribeStates[i]);
        }
    }

    private void initListener() {
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(KEY, getSubscribeState());
                setResult(RESULT_CODE, intent);
                finish();
            }
        });
    }

    private boolean[] getSubscribeState() {
        boolean[] subscribeStates = new boolean[NewsCategory.CATEGORY_CNT + 1];
        subscribeStates[0] = true;
        for (int i = 1; i <= NewsCategory.CATEGORY_CNT; ++i) {
            View categoryItem = findViewById(NewsCategory.codeToId(i));
            CheckBox cb = categoryItem.findViewById(R.id.checkBox);
            subscribeStates[i] = cb.isChecked();
        }
        return subscribeStates;
    }

}
