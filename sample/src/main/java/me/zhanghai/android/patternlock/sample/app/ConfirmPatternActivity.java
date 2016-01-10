/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.patternlock.sample.app;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import me.zhanghai.android.patternlock.PatternView;
import me.zhanghai.android.patternlock.sample.util.PatternLockUtils;
import me.zhanghai.android.patternlock.sample.util.PreferenceContract;
import me.zhanghai.android.patternlock.sample.util.PreferenceUtils;
import me.zhanghai.android.patternlock.sample.util.ThemeUtils;

public class ConfirmPatternActivity extends me.zhanghai.android.patternlock.ConfirmPatternActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ThemeUtils.applyTheme(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isStealthModeEnabled() {
        return !PreferenceUtils.getBoolean(PreferenceContract.KEY_PATTERN_VISIBLE,
                PreferenceContract.DEFAULT_PATTERN_VISIBLE, this);
    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        return PatternLockUtils.isPatternCorrect(pattern, this);
    }

    @Override
    protected void onForgotPassword() {

        startActivity(new Intent(this, ResetPatternActivity.class));

        // Finish with RESULT_FORGOT_PASSWORD.
        super.onForgotPassword();
    }
}
