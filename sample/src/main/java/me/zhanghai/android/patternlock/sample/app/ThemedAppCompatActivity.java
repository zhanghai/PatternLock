/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.patternlock.sample.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.zhanghai.android.patternlock.sample.util.ThemeUtils;

public class ThemedAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeUtils.applyTheme(this);

        super.onCreate(savedInstanceState);
    }
}
