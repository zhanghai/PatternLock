/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.app;

import android.app.Activity;
import android.os.Bundle;

import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.AppUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.setupActionBar(this);

        setContentView(R.layout.main_activity);
    }
}
