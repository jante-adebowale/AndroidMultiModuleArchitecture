plugins {
    alias(libs.plugins.mtm.android.library)
    alias(libs.plugins.mtm.retrofit)
    alias(libs.plugins.mtm.android.koin)
    alias(libs.plugins.mtm.kotlin.serialization)
}

android {
    namespace = "com.janteadebowale.data"
}

dependencies {

    api(projects.core.common)
    api(projects.core.network)
    api(projects.core.domain)

    //Datastore
    implementation (libs.androidx.datastore.preferences)
    // Crypto
    implementation(libs.androidx.security.crypto.ktx)

}