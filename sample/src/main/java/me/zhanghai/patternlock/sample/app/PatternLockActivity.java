/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.AppUtils;
import me.zhanghai.patternlock.sample.util.PatternLockUtils;

public class PatternLockActivity extends Activity {

    private static final String KEY_CONFIRM_STARTED = "confirmStarted";

    private boolean confirmStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.setupActionBarDisplayUp(this);

        setContentView(R.layout.pattern_lock_activity);

        if (savedInstanceState != null) {
            confirmStarted = savedInstanceState.getBoolean(KEY_CONFIRM_STARTED);
        }
        if (!confirmStarted) {
            PatternLockUtils.confirmPatternIfHas(this);
            confirmStarted = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_CONFIRM_STARTED, confirmStarted);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AppUtils.navigateUp(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (PatternLockUtils.checkConfirmPatternResult(this, requestCode, resultCode)) {
            confirmStarted = false;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
