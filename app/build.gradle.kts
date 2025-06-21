plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.passwordguardian"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.passwordguardian"
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
        // This version MUST be compatible with your Compose BOM.
        // For BOM 2024.06.00, Kotlin Compiler Extension 1.5.11 is typically compatible.
        // Always check the official documentation for the latest mapping:
        // https://developer.android.com/jetpack/compose/bom
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // ... (your existing non-Compose dependencies like core-ktx, lifecycle, activity-compose)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.compose.material3:material3:1.1.1") // Keep explicit for now if you prefer

    // --- COMPOSE DEPENDENCIES ---
    // 1. Declare the Compose BOM (Bill of Materials) for 'implementation'
    implementation(platform("androidx.compose:compose-bom:2024.06.00")) // Make sure this is still 2024.06.00

    // 2. Add your Compose libraries WITHOUT explicit versions for regular app code.
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-extended") // Version managed by BOM

    // --- ExoPlayer (Media3) Dependencies ---
    implementation("androidx.media3:media3-exoplayer:1.1.0")
    implementation("androidx.media3:media3-ui:1.1.0")
    implementation("androidx.media3:media3-common:1.1.0")

    // --- Kotlin Coroutines ---
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // --- TESTING DEPENDENCIES ---
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // *** IMPORTANT CHANGE FOR TEST DEPENDENCIES ***
    // Declare the Compose BOM explicitly for androidTestImplementation as well
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00")) // Add this line!

    // Compose UI testing library (now managed by the BOM for androidTest)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") // No version here!

    // Debugging and tooling features for Compose
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}