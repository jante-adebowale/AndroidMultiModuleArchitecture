plugins {
    alias(libs.plugins.mtm.android.library.compose)
}

android {
    namespace = "com.janteadebowale.core.ui"
    sourceSets {
        getByName("main") {
            res {
                srcDirs("src/main/res")
            }
        }
    }
}
dependencies {
    api(projects.core.designsystem)
}