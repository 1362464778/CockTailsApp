// build.gradle.kts (Project-level)

// 在这里声明插件的版本，但并不将它们应用到具体模块
// 'apply false' 意味着版本在这里被定义，但具体模块需要自己决定是否应用这个插件
plugins {
    // 安卓应用插件
    id("com.android.application") version "8.10.0" apply false
    // Kotlin 安卓插件
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
}
