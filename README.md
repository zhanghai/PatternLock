# PatternLock

An Android library for pattern lock.

## PatternLock or LockPattern?

The original view in AOSP is named `LockPatternView`, however I believe it is named so because it displays the (screen lock) pattern, instead of the pattern as a lock. Since this library is to provide the pattern as a locking mechanism, I'd prefer naming it `PatternLock`, and for simplicity the view is renamed to be `PatternView`.

## Design

This library aims to provide the basic but extensible build blocks for implementing pattern lock mechanism in Android app. So the common usage will be extending the basic activities classes provided and overriding methods according to your need.

This library also aims to be elegant. Code taken from AOSP was slightly refactored and renamed to be clear, and the `PatternView` now utilizes the Android resource system for customization.

Currently this library is partly tailored to my own needs.

* The `PatternView` code is taken just before the Material design adjusted its animation and styling, which keeps its Holo style, consistent to my aesthetics and production app.

* This library is currently ICS-compatible, since Android 2.x are phasing out day by day, and I don't need to support them. (But it can be refactored to support older versions if anyone has the time.)

*  This library defaults its language to zh-CN, because my production app primarily focuses on Chinese users, but this behavior can be changed by swapping `strings.xml`.

## Usage

### Styling

First of all, you need to include the default styling in your theme, by adding:

``` xml
<item name="patternViewStyle">@style/PatternView.Holo</item>
<!-- Or PatternView.Holo.Dark, or your own style extending these two or not. -->
```

Or you can utilize the resource overriding trick by copying the `layout/base_pattern_activity.xml` from the library source to your application source, and modify it there (for instance PatternView attributes). Changes will override the original layout in this library.

Available `PatternView` attributes are, as in `attrs.xml`:

``` xml
<declare-styleable name="PatternView">
    <!-- Defines the aspect to use when drawing PatternView. -->
    <attr name="aspect">
        <!-- Square; the default value. -->
        <enum name="square" value="0" />
        <enum name="lock_width" value="1" />
        <enum name="lock_height" value="2" />
    </attr>
    <!-- Defines the regular pattern color. -->
    <attr name="regularColor" format="color|reference" />
    <!-- Defines the error color. -->
    <attr name="errorColor" format="color|reference" />
    <!-- Defines the success color. -->
    <attr name="successColor" format="color|reference"/>
    <!-- Defines the color to use when drawing PatternView paths. -->
    <attr name="pathColor" format="color|reference" />
    <attr name="dotDrawableDefault" format="reference" />
    <attr name="dotDrawableTouched" format="reference" />
    <attr name="circleDrawableDefault" format="reference" />
    <attr name="circleDrawable" format="reference" />
    <attr name="arrowUpDrawable" format="reference" />
</declare-styleable>
```

And built-in styles, as in `styles.xml`:

``` xml
<style name="PatternView">
    <item name="aspect">square</item>
</style>

<style name="PatternView.Holo">
    <item name="regularColor">@android:color/white</item>
    <item name="errorColor">@android:color/holo_red_light</item>
    <item name="successColor">@android:color/holo_green_light</item>
    <item name="pathColor">@android:color/white</item>
    <item name="dotDrawableDefault">@drawable/pl_patternview_dot_default</item>
    <item name="dotDrawableTouched">@drawable/pl_patternview_dot_touched</item>
    <item name="circleDrawableDefault">@drawable/pl_patternview_circle_default_alpha</item>
    <item name="circleDrawable">@drawable/pl_patternview_circle_alpha</item>
    <item name="arrowUpDrawable">@drawable/pl_patternview_arrow_alpha</item>
</style>

<style name="PatternView.Holo.Light">
    <item name="regularColor">@android:color/black</item>
    <item name="pathColor">@android:color/black</item>
</style>
```

### Implementing

As stated above, the common usage will be extending instead of giving `Intent` extras, because the actions to perform generally requires a `Context` or even a `Activity`, in which a simple callback can be difficult or even leaking `Activity` instances.

Set pattern activity example:

``` java
public class SampleSetPatternActivity extends SetPatternActivity {

    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        String patternSha1 = PatternUtils.patternToSha1String(pattern);
        // TODO: Save patternSha1 in SharedPreferences.
    }
}
```

Confirm pattern activity example:

``` java
public class SampleConfirmPatternActivity extends ConfirmPatternActivity {

    @Override
    protected void onForgotPassword() {

        startActivity(new Intent(this, YourResetPatternActivity.class));

        // Finish with RESULT_FORGOT_PASSWORD.
        super.onForgotPassword();
    }

    @Override
    protected boolean isStealthModeEnabled() {
        // TODO: Return the value from SharedPreferences.
        return false;
    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        // TODO: Get saved pattern sha1.
        String patternSha1 = null;
        return TextUtils.equals(PatternUtils.patternToSha1(pattern), patternSha1);
    }
}
```

## Differences with android-lockpattern

I know there is already a library named [android-lockpattern](https://code.google.com/p/android-lockpattern/), and I tried it for one day before I started writing this "yet another".

 So here are the major differences.

* That project is hosted on Google Code using `hg`, while this project is hosted on GitHub using `git`.

* That project is Eclipse based (?), while this project is written using Android Studio.

* That project is prefixing its resources using `alp_42447968`, while this project is prefixing its resources using `pl`, and I prefer the simplicity to that "security".

* That project provides a bunch of mechanisms and extras for its `Activity`, none of which I found suitable for my use case (The `Intent` extra is replaced by `PendingIntent`, which I would prefer simplicity again here), while this project only provides a `Intent` extra and some methods to override to meet your need.

* This project lacks some source code documentation and sample application compared to that project, but generally you only need to read the names of those protected methods to learn its usage. (But I know documentation and sample apps are good, and I will complete them if more people are using this project.)
