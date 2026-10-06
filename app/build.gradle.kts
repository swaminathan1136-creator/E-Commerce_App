plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.e_commerce_app"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.e_commerce_app"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // Android Views / Java
    implementation("androidx.appcompat:appcompat:1.8.0")

    // AndroidX core
    implementation("androidx.core:core:1.17.0")

    // Material Design components
    implementation("com.google.android.material:material:1.13.0")
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)

    // Testing
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}