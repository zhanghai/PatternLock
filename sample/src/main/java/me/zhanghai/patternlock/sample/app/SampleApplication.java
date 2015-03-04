/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import me.zhanghai.patternlock.sample.util.ThemeUtils;

public class SampleApplication extends Application {

    private static final ActivityLifecycleCallbacks CALLBACKS = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ThemeUtils.applyTheme(activity);
        }
        @Override
        public void onActivityStarted(Activity activity) {}
        @Override
        public void onActivityResumed(Activity activity) {}
        @Override
        public void onActivityPaused(Activity activity) {}
        @Override
        public void onActivityStopped(Activity activity) {}
        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
        @Override
        public void onActivityDestroyed(Activity activity) {}
    };

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(CALLBACKS);
    }
}
