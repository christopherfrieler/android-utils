# IMPORTANT 

**This project has been abandoned.**

The provided `Strings`-functions can be used from the Kotlin standard-library, thanks to Kotlin's Java interoperability
even from Java apps. And the `Preconditions` aren't worth an extra library project.

However, since android-utils is still available via jcenter, the code will remain here and stay open-source. 

# Android-Utils

android-utils is a library for Android apps to provide some convenient utility-functions that are not part of the java8
standard-library or are neither usable in your app due to API level constraints nor backported in
[streamsupport](https://github.com/streamsupport/streamsupport).


# Usage

Make sure you have 'jcenter' added to your repositories in your build.gradle file:
```
allprojects {
    repositories {
        jcenter()
    }
}
```

Then add android-utils to your dependencies:
```
dependencies {
    implementation 'rocks.frieler.android:android-utils:0.5.0'
}
```

Now you can use the features briefly explained below: 

# Overview

## Strings

`rocks.frieler.android.utils.Strings` contains all kind of utility-functions dealing with Strings.

## Preconditions

`rocks.frieler.android.utils.Preconditions` contains utility-functions to check the preconditions of functions.
