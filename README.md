# PatternLock

An Android library for pattern lock.

![Sample](./image/sample_small.png)

## PatternLock or LockPattern?

The original view in AOSP is named `LockPatternView`, however I believe it is named so because it displays the (screen lock) pattern, instead of the pattern as a lock. Since this library is to provide the pattern as a locking mechanism, I'd prefer naming it `PatternLock`, and for simplicity， the view is renamed to `PatternView`.

## Design

This library aims to provide the basic but extensible building blocks for implementing pattern lock mechanism in an Android app. So the common usage will be extending the base Activity classes provided and overriding methods according to your need.

This library also aims to be elegant. Code taken from AOSP was slightly refactored and renamed to be clear, and the `PatternView` now utilizes the Android resource system for customization. Moreover, tweaks for stealth mode and haptic feedback is done for better behavior.

Currently this library is partly tailored to my own needs:

* The `PatternView` code is taken just before the Material design adjusted its animation and styling, which keeps its Holo style, consistent to my aesthetics and production app.

* This library is currently ICS-compatible, since Android 2.x are phasing out day by day, and I don't need to support them. (But it can be refactored to support older versions if anyone has the time.)

## Usage

Here are some detailed usage documentation, and you can always refer to the sample app source for a working implementation.

### Styling

First of all, you need to include the default styling in your theme, by adding:

```xml
<item name="patternViewStyle">@style/PatternView.Holo</item>
<!-- Or PatternView.Holo.Light, or your own style extending these two or not. -->
```

Or you can utilize the resource overriding trick by copying the `layout/base_pattern_activity.xml` from the library source to your application source, and modify it there (for instance PatternView attributes). Changes will override the original layout in this library.

Available `PatternView` attributes are, as in `attrs.xml`:

```xml
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

```xml
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

As stated above, the common usage will be extending or checking result instead of giving `Intent` extras, because the actions to perform generally requires a `Context` or even a `Activity`, in which a simple callback can be difficult or even leaking `Activity` instances.

Set pattern activity example:

```java
public class SampleSetPatternActivity extends SetPatternActivity {

    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        String patternSha1 = PatternUtils.patternToSha1String(pattern);
        // TODO: Save patternSha1 in SharedPreferences.
    }
}
```

Confirm pattern activity example:

```java
public class SampleConfirmPatternActivity extends ConfirmPatternActivity {

    @Override
    protected boolean isStealthModeEnabled() {
        // TODO: Return the value from SharedPreferences.
        return false;
    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        // TODO: Get saved pattern sha1.
        String patternSha1 = null;
        return TextUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha1);
    }

    @Override
    protected void onForgotPassword() {

        startActivity(new Intent(this, YourResetPatternActivity.class));

        // Finish with RESULT_FORGOT_PASSWORD.
        super.onForgotPassword();
    }
}
```

Note that protected fields inherited from `BasePatternActivity`, such as `messageText` and `patternView`, are also there ready for your customization.

## Differences with android-lockpattern

I know there is already a library named [android-lockpattern](https://code.google.com/p/android-lockpattern/), and I tried it for a whole day before I started writing this "yet another".

 So here are the major differences.

* That project is hosted on Google Code using `hg`, while this project is hosted on GitHub using `git`.

* That project is Eclipse based, while this project is developed using Android Studio.

* That project is prefixing its resources using `alp_42447968`, while this project is prefixing its resources using `pl`, and I prefer simplicity to that "security".

* That project provides a bunch of mechanisms and extras for its `Activity` (and with some private methods :( ), none of which I found suitable for my use case, while this project provides a bunch of methods to override to meet your need.

* That project has a demo but it is close-sourced, while this project provides an open source sample application with a working pattern lock mechanism implementation.

## License

    Copyright 2015 Zhang Hai

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
