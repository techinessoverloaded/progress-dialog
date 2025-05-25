plugins {
    id("com.android.application")
    kotlin("android")
}

val javaVersion = JavaVersion.VERSION_17

android {
    namespace = "com.techiness.progressdialogexample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.techiness.progressdialogexample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
    //implementation(project(":progressdialoglibrary"))
    val progressVersion = "1.5.1"
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
//    implementation("com.github.techinessoverloaded:progress-dialog:$progressVersion")
    implementation(project(":progressdialoglibrary"))
}
repositories {
    mavenCentral()
}