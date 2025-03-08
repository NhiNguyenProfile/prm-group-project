plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.prm392_project"
    compileSdk = 35
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.example.prm392_project"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation(libs.legacy.support.v4)
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation(libs.recyclerview)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}