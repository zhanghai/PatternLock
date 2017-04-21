/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.patternlock.sample.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import me.zhanghai.android.patternlock.sample.util.AppUtils;
import me.zhanghai.android.patternlock.sample.util.FragmentUtils;
import me.zhanghai.android.patternlock.sample.util.PatternLockUtils;

public class PatternLockActivity extends ThemedAppCompatActivity
        implements PatternLockUtils.OnConfirmPatternResultListener {

    private static final String KEY_CONFIRM_PATTERN_STARTED = "confirm_pattern_started";
    private static final String KEY_SHOULD_ADD_FRAGMENT = "should_add_fragment";

    private boolean mConfirmPatternStarted = false;
    private boolean mShouldAddFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.setActionBarDisplayUp(this);

        // Calls ensureSubDecor().
        findViewById(android.R.id.content);

        if (savedInstanceState != null) {
            mConfirmPatternStarted = savedInstanceState.getBoolean(KEY_CONFIRM_PATTERN_STARTED);
            mShouldAddFragment = savedInstanceState.getBoolean(KEY_SHOULD_ADD_FRAGMENT);
        } else {
            mShouldAddFragment = !PatternLockUtils.hasPattern(this);
        }
        if (!mConfirmPatternStarted) {
            PatternLockUtils.confirmPatternIfHas(this);
            mConfirmPatternStarted = true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (mShouldAddFragment) {
            FragmentUtils.add(PatternLockFragment.newInstance(), this, android.R.id.content);
            mShouldAddFragment = false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_CONFIRM_PATTERN_STARTED, mConfirmPatternStarted);
        outState.putBoolean(KEY_SHOULD_ADD_FRAGMENT, mShouldAddFragment);
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
            // Do nothing.
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConfirmPatternResult(boolean successful) {
        if (successful) {
            // Throws IllegalStateException if we add our fragment now.
            mShouldAddFragment = true;
        } else {
            finish();
        }
    }
}
