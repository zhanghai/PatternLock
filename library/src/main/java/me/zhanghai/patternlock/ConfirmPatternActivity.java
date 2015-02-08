/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock;

import android.os.Bundle;
import android.view.View;

import java.util.List;

// For AOSP implementations, see:
// https://android.googlesource.com/platform/packages/apps/Settings/+/master/src/com/android/settings/ConfirmLockPattern.java
// https://android.googlesource.com/platform/frameworks/base/+/43d8451/policy/src/com/android/internal/policy/impl/keyguard/KeyguardPatternView.java
// https://android.googlesource.com/platform/frameworks/base/+/master/packages/Keyguard/src/com/android/keyguard/KeyguardPatternView.java
public class ConfirmPatternActivity extends BasePatternActivity
        implements PatternView.OnPatternListener {

    private static final String KEY_NUM_FAILED_ATTEMPTS = "num_failed_attempts";

    public static final int RESULT_FORGOT_PASSWORD = RESULT_FIRST_USER;

    protected int numFailedAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        messageText.setText(R.string.pl_draw_pattern_to_unlock);
        patternView.setInStealthMode(isStealthModeEnabled());
        patternView.setOnPatternListener(this);
        leftButton.setText(R.string.pl_cancel);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel();
            }
        });
        rightButton.setText(R.string.pl_forgot_pattern);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onForgotPassword();
            }
        });
        ViewAccessibilityCompat.announceForAccessibility(messageText, messageText.getText());

        if (savedInstanceState == null) {
            numFailedAttempts = 0;
        } else {
            numFailedAttempts = savedInstanceState.getInt(KEY_NUM_FAILED_ATTEMPTS);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_NUM_FAILED_ATTEMPTS, numFailedAttempts);
    }

    @Override
    public void onPatternStart() {

        removeClearPatternRunnable();

        // Set display mode to correct to ensure that pattern can be in stealth mode.
        patternView.setDisplayMode(PatternView.DisplayMode.Correct);
    }

    @Override
    public void onPatternCellAdded(List<PatternView.Cell> pattern) {}

    @Override
    public void onPatternDetected(List<PatternView.Cell> pattern) {
        if (isPatternCorrect(pattern)) {
            onConfirmed();
        } else {
            messageText.setText(R.string.pl_wrong_pattern);
            patternView.setDisplayMode(PatternView.DisplayMode.Wrong);
            postClearPatternRunnable();
            ViewAccessibilityCompat.announceForAccessibility(messageText, messageText.getText());
            onWrongPattern();
        }
    }

    @Override
    public void onPatternCleared() {
        removeClearPatternRunnable();
    }

    protected boolean isStealthModeEnabled() {
        return false;
    }

    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        return true;
    }

    protected void onConfirmed() {
        setResult(RESULT_OK);
        finish();
    }

    protected void onWrongPattern() {
        ++numFailedAttempts;
    }

    protected void onCancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    protected void onForgotPassword() {
        setResult(RESULT_FORGOT_PASSWORD);
        finish();
    }
}
