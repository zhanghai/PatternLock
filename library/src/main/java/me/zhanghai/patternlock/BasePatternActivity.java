/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BasePatternActivity extends Activity {

    private static final int CLEAR_PATTERN_DELAY_MILLI = 2000;

    protected TextView messageText;
    protected PatternView patternView;
    protected LinearLayout buttonContainer;
    protected Button leftButton;
    protected Button rightButton;

    private final Runnable clearPatternRunnable = new Runnable() {
        public void run() {
            // clearPattern() resets display mode to DisplayMode.Correct.
            patternView.clearPattern();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pl_base_pattern_activity);
        messageText = (TextView)findViewById(R.id.pl_message_text);
        patternView = (PatternView)findViewById(R.id.pl_pattern);
        buttonContainer = (LinearLayout)findViewById(R.id.pl_button_container);
        leftButton = (Button)findViewById(R.id.pl_left_button);
        rightButton = (Button)findViewById(R.id.pl_right_button);
    }

    protected void removeClearPatternRunnable() {
        patternView.removeCallbacks(clearPatternRunnable);
    }

    protected void postClearPatternRunnable() {
        removeClearPatternRunnable();
        patternView.postDelayed(clearPatternRunnable, CLEAR_PATTERN_DELAY_MILLI);
    }
}
