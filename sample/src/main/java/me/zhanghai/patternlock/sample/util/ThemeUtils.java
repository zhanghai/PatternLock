/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.util;

import android.app.Activity;
import android.content.Context;

import me.zhanghai.patternlock.sample.R;

public class ThemeUtils {

    private ThemeUtils() {}

    private static final int[] THEME_IDS = new int[] {
            R.style.AppTheme,
            R.style.AppTheme_Light
    };

    public static int getThemeId(Context context) {
        int index = Integer.valueOf(PreferenceUtils.getString(PreferenceContract.KEY_THEME,
                PreferenceContract.DEFAULT_THEME, context));
        return THEME_IDS[index];
    }

    public static void applyTheme(Activity activity) {
        int themeId = getThemeId(activity);
        activity.setTheme(themeId);
    }
}
