/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.AppUtils;
import me.zhanghai.patternlock.sample.util.PatternLockUtils;
import me.zhanghai.patternlock.sample.util.ToastUtils;

public class ResetPatternActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.setupActionBar(this);

        setContentView(R.layout.reset_pattern_activity);

        findViewById(R.id.reset_pattern_ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatternLockUtils.clearPattern(ResetPatternActivity.this);
                ToastUtils.show(R.string.pattern_reset, ResetPatternActivity.this);
                finish();
            }
        });

        findViewById(R.id.reset_pattern_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
