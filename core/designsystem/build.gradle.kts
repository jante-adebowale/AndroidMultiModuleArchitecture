plugins {
    alias(libs.plugins.mtm.android.library.compose)
}

android {
    namespace = "com.janteadebowale.designsystem"
    sourceSets {
        getByName("main") {
            res {
                srcDirs("src/main/res")
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    api(libs.androidx.material3)

    api(libs.material)
    api(libs.ui.text.google.fonts)

    // Extended Icons
    implementation(libs.androidx.material.icons.extended)
}