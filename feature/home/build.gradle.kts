plugins {
    alias(libs.plugins.mtm.android.feature)
}

android {
    namespace = "com.janteadebowale.feature.home"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
}