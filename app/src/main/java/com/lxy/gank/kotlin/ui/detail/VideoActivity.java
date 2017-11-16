package com.lxy.gank.kotlin.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lxy.gank.kotlin.R;

/**
 * Created by lxy on 2017/11/16.
 */

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String value = getIntent().getStringExtra("key");
        TextView tv = findViewById(R.id.tv);
        tv.setText(value);
    }
}
