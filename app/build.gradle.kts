plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)

    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.example.mathapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mathapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
//    testOptions {
//        unitTests.isIncludeAndroidResources = true
//        unitTests.all {
//            useJUnitPlatform()
//        }
//    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("io.github.jan-tennert.supabase:postgrest-kt:0.7.6")
    // implementation("io.github.jan-tennert.supabase:storage-kt:3.0.3")
    //noinspection UseTomlInstead
    implementation("io.ktor:ktor-client-cio:2.3.3")
    //noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    //noinspection UseTomlInstead
    // implementation("io.github.jan-tennert.supabase:gotrue-kt:3.0.0")

    // implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")

    // implementation("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")

    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui:1.7.5")
    //noinspection UseTomlInstead
    implementation ("androidx.compose.material3:material3:1.3.1")
    //noinspection UseTomlInstead
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.ui.test.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.datastore.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}