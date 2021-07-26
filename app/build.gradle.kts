plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.training.intro"
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode(1)
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    buildFeatures{
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val kotlin_version = "1.5.20"
val material_version = "1.4.0"
val constraint_layout_version = "2.0.4"
val kotlin_core = "1.6.0"
val app_compat_version = "1.5.0"
val junit_version = "4.13.2"
val ext_junit_version = "1.1.3"
val esspresso_version = "3.4.0"

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.0-alpha05")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.0-alpha05")

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}