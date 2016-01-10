/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.patternlock;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

class ViewAccessibilityCompat {

    private ViewAccessibilityCompat() {}

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void announceForAccessibility(View view, CharSequence announcement) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.announceForAccessibility(announcement);
        } else {
            Context context = view.getContext();
            AccessibilityManager manager = (AccessibilityManager)context.getSystemService(
                    Context.ACCESSIBILITY_SERVICE);
            if (!manager.isEnabled()) {
                return;
            }
            // According to platform_packages_apps/Camera2
            // com.android.camera.util.AccessibilityUtils.java
            AccessibilityEvent event = AccessibilityEvent.obtain(
                    AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED);
            event.setSource(view);
            event.setClassName(view.getClass().getName());
            event.setPackageName(context.getPackageName());
            event.setEnabled(view.isEnabled());
            event.getText().add(announcement);
            manager.sendAccessibilityEvent(event);
        }
    }
}
