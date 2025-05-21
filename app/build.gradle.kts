plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.mtm.android.application)
    alias(libs.plugins.mtm.android.application.compose)
}

android {
    namespace = "com.janteadebowale.androidmultimodulearchitecture"
//    compileSdk = 35

    defaultConfig {
//        applicationId = "com.janteadebowale.androidmultimodulearchitecture"
//        minSdk = 24
//        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
//    buildFeatures {
//        compose = true
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

//
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)


    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)

    //koin
    implementation(libs.bundles.koin.compose)

    //Timber
    implementation (libs.timber)

    //Splash Api
    implementation (libs.androidx.core.splashscreen)

    //Modules
    implementation(projects.core.data)
    implementation(projects.core.network)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.datastore)
    implementation(projects.core.ui)

    //Features
    implementation(projects.feature.auth)
    implementation(projects.feature.home)
    implementation(projects.feature.purchase)
    implementation(projects.feature.cash)

}