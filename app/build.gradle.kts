// app/build.gradle.kts (Module-level)

plugins {
    // 在这里应用项目级文件中定义的插件
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    // 使用最新的稳定版 SDK
    compileSdk = 35
    namespace = "com.auth.TALESS" // 必须提供命名空间

    defaultConfig {
        applicationId = "com.authandroid_smartcookies.smartcookie"
        minSdk = 27
        // 目标 SDK 必须跟上 compileSdk，以符合 Google Play 政策
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        // 开启 ViewBinding 和 DataBinding
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false // 注意：在 .kts 文件中是 isMinifyEnabled
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    // 提升 Java 和 Kotlin 的版本以兼容新的库和 AGP
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.activity:activity:1.10.1")
    // 定义一个变量来统一管理 Navigation 库的版本
    val navVersion = "2.7.7"

    // Base & UI
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Navigation (推荐使用 KTX 版本)
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Preferences (推荐使用 KTX 版本)
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Glide (图片加载)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // 在 .kts 文件中，annotationProcessor 变成了 kapt
    // kapt("com.github.bumptech.glide:compiler:4.16.0")
    // 注意：如果你的项目还没有配置 kapt，需要在 plugins 块中添加 id("kotlin-kapt")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
