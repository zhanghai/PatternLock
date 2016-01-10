/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.patternlock.sample.preference;

import android.app.Activity;
import android.support.v7.preference.PreferenceDialogFragmentCompat;

import me.zhanghai.android.patternlock.sample.R;
import me.zhanghai.android.patternlock.sample.util.PatternLockUtils;
import me.zhanghai.android.patternlock.sample.util.ToastUtils;

public class ClearPatternPreferenceDialogFragment extends PreferenceDialogFragmentCompat {

    public static ClearPatternPreferenceDialogFragment newInstance() {
        return new ClearPatternPreferenceDialogFragment();
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            Activity activity = getActivity();
            PatternLockUtils.clearPattern(activity);
            ToastUtils.show(R.string.pattern_cleared, activity);
        }
    }
}
