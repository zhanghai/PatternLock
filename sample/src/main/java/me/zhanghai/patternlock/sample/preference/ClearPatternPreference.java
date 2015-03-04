/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.PatternLockUtils;
import me.zhanghai.patternlock.sample.util.ToastUtils;

public class ClearPatternPreference extends DialogPreference {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearPatternPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ClearPatternPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClearPatternPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearPatternPreference(Context context) {
        super(context);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            Context context = getContext();
            PatternLockUtils.clearPattern(context);
            ToastUtils.show(R.string.pattern_cleared, context);
        }
    }
}
