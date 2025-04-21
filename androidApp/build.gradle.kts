plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.schedulingapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.schedulingapp.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kotlin {
        jvmToolchain(11)
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
dependencies {
    implementation(project(":shared"))
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1") // ✅ ESSENCIAL PARA COMPOSE

    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.compose.ui:ui:1.3.0")
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.foundation:foundation:1.3.0")
    implementation("androidx.compose.animation:animation:1.3.0")
    implementation("androidx.activity:activity-ktx:1.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
}



configurations.all {
    resolutionStrategy {
        force("androidx.lifecycle:lifecycle-livedata-core:2.5.1")
        force("androidx.profileinstaller:profileinstaller:1.3.1")
    }
}
