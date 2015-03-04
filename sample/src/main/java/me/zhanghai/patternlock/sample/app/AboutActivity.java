/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.patternlock.sample.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import me.zhanghai.patternlock.sample.R;
import me.zhanghai.patternlock.sample.util.AppUtils;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.setupActionBarDisplayUp(this);

        setContentView(R.layout.about_activity);

        String version = getString(R.string.about_version,
                AppUtils.getPackageInfo(this).versionName);
        ((TextView) findViewById(R.id.about_version_text)).setText(version);
        ((TextView) findViewById(R.id.about_github_text))
                .setMovementMethod(LinkMovementMethod.getInstance());
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
}
