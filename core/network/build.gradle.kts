plugins {
    alias(libs.plugins.mtm.android.library)
    alias(libs.plugins.mtm.retrofit)
}

android {
    namespace = "com.janteadebowale.core.network"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation (libs.timber)
    implementation(projects.core.common)
    implementation(projects.core.domain)
}