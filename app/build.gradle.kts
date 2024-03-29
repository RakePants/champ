plugins {
    //kapt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.hacktask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hacktask"
        minSdk = 24
        targetSdk = 34
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
    implementation(libs.play.services.location)
    val compose_compiler = "1.4.3"
    val compose_version = "1.3.3"
    val compose_material_icons = "1.3.1"

    val navigation_version = "2.7.7"
    val coroutines_version = "1.7.3"
    val retrofit_version = "2.9.0"
    val okhttp_version = "4.12.0"
    val picasso_version = "2.8"
    val dagger2_version = "2.50"
    val hilt_version = "2.51"
    val hilt_navigation_compose_version = "1.2.0"
    val hilt_compiler_version = "1.2.0"
    val hilt_work_version = "1.2.0"
    val orbit_version = "7.0.0"
    val work_runtime_version = "2.7.1"
    val kotlin_version = "1.8.10"

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")

    //Json

    //Picasso
    implementation("com.squareup.picasso:picasso:$picasso_version")

    //Orbit
    implementation("org.orbit-mvi:orbit-core:$orbit_version")
    // or, if on Android:
    implementation("org.orbit-mvi:orbit-viewmodel:$orbit_version")
    // If using Jetpack Compose include
    implementation("org.orbit-mvi:orbit-compose:$orbit_version")

    //Dagger2
//    kapt("com.google.dagger:dagger-compiler:$dagger2Version")
//    implementation("com.google.dagger:dagger:$dagger2Version")

    //Dagger-hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version")
    kapt("androidx.hilt:hilt-compiler:$hilt_compiler_version")

    implementation("androidx.compose.runtime:runtime:$compose_version")
    implementation("androidx.compose.runtime:runtime-livedata:$compose_version")

    implementation("androidx.work:work-runtime-ktx:$work_runtime_version")

    implementation("androidx.hilt:hilt-work:$hilt_work_version")



    implementation("androidx.compose.material3:material3-window-size-class-android:1.2.1")

    //viewmodel compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.02"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}