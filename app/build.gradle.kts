import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.calex.moviesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.calex.moviesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildFeatures.buildConfig = true

        val properties = Properties()
        properties.load(project.rootProject.file("apikeys.properties").inputStream())
        buildConfigField("String", "MoviesDB_API_KEY", properties.getProperty("MoviesDB_API_KEY"))
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

    // Used to handle Retrofit for network calls
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Used to handle gson, to map JSON files to Kotlin objects
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Used to handle Image loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Used to handle navigation within the app
    implementation("androidx.navigation:navigation-compose:2.7.7")

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