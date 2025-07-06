// settings.gradle.kts

pluginManagement {
    repositories {
        // 定义从哪里下载 Gradle 插件 (比如 AGP)
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // FAIL_ON_PROJECT_REPOS 是一种安全设置，确保所有仓库都在这里统一声明
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 定义从哪里下载项目依赖库 (比如 AppCompat, Material Design)
        google()
        mavenCentral()
        // 如果你需要从其他地方下载，比如 JitPack，可以在这里添加
        // maven { url = uri("https://jitpack.io") }
    }
}

// 项目的基本设置
rootProject.name = "TALESS-Cocktails" // 你的项目名称
include(":app") // 包含 app 模块

