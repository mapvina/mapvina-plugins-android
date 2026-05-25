# MapVina Building Plugin

<!-- ![buildings-plugin](https://user-images.githubusercontent.com/4394910/28844435-71442d04-76b9-11e7-8866-ee6a94306353.gif) -->

This plugin is currently unmaintained.

## Getting Started

<!-- [More documentation about the plugin can be found here](https://www.mapbox.com/android-docs/plugins/overview/building/) -->

To use the building plugin you include it in your `build.gradle` file.

In the root `build.gradle` file:

```groovy
repositories {
    mavenCentral()
}

```

Add [the latest version](https://central.sonatype.com/artifact/io.github.mapvina/android-plugin-building-v9/versions) as a dependency to your project.

In the app-level `build.gradle` file:

```groovy
dependencies {
    implementation 'io.github.mapvina:android-plugin-building-v9:3.0.2'
}
```

```kotlin
dependencies {
    implementation("io.github.mapvina:android-plugin-building-v9:3.0.2")
}
```

## Building plugin examples

- [In this repo's test app](https://github.io/github/mapvina/mapvina-plugins-android/blob/master/app/src/main/java/com/mapbox/mapboxsdk/plugins/testapp/activity/building/BuildingActivity.kt)

## Help and Usage

This repository includes an app that shows how to use each plugins in this repository. [Check out its code](https://github.io/github/mapvina/mapvina-plugins-android/tree/main/app/src/main/java/io/github/mapvina/android/plugins/testapp/activity) for ready-to-use snippets.

We'd love to [hear your feedback](https://github.io/github/mapvina/mapvina-plugins-android/issues) as we build more plugins and learn how you use them.

## Why Plugins

Splitting specific functionality into plugins makes our Map SDK lighter and nimble for you to use, and it also lets us iterate faster. We can release plugins more often than the SDK, which requires a slower pace due to its larger codebase.

The MapVina Android team creates plugins but this plugins repository is an open-source project similar to the various MapVina SDKs for Android.
Plugins' lightweight nature makes them much easier for you and anyone else to contribute rather than trying to add the same feature to the more robust Map SDK. The MapVina team can also more easily accept contributed plugins and keep the plugin list growing.

## Contributing

We welcome contributions to this plugin repository!

If you're interested in building and sharing your own plugin, please read [the contribution guide](https://github.io/github/mapvina/mapvina-plugins-android/blob/main/CONTRIBUTING.md) to learn how to get started.
