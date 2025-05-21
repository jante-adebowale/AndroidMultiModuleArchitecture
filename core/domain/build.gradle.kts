plugins {
    alias(libs.plugins.mtm.android.library)
}

android {
    namespace = "com.janteadebowale.core.domain"

}

dependencies {
    api(projects.core.model)
    api(projects.core.common)
    implementation(libs.kotlinx.coroutines.core)
}