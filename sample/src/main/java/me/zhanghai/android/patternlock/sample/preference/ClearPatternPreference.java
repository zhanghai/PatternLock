/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.patternlock.sample.preference;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.AttributeSet;

public class ClearPatternPreference extends DialogPreference {

    // As in PreferenceFragmentCompat, because we want to ensure that at most one dialog is showing.
    private static final String DIALOG_FRAGMENT_TAG =
            "android.support.v7.preference.PreferenceFragment.DIALOG";

    public static boolean onDisplayPreferenceDialog(PreferenceFragmentCompat preferenceFragment,
                                                    Preference preference) {

        if (preference instanceof ClearPatternPreference) {
            // getChildFragmentManager() will lead to looking for target fragment in the child
            // fragment manager.
            FragmentManager fragmentManager = preferenceFragment.getFragmentManager();
            if(fragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null) {
                ClearPatternPreferenceDialogFragment dialogFragment =
                        ClearPatternPreferenceDialogFragment.newInstance(preference.getKey());
                dialogFragment.setTargetFragment(preferenceFragment, 0);
                dialogFragment.show(fragmentManager, DIALOG_FRAGMENT_TAG);
            }
            return true;
        }

        return false;
    }

    public ClearPatternPreference(Context context, AttributeSet attrs, int defStyleAttr,
                                  int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ClearPatternPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClearPatternPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearPatternPreference(Context context) {
        super(context);
    }
}
