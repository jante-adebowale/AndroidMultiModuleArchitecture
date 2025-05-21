plugins {
    `kotlin-dsl`
}

group = "com.janteadebowale.mtm.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "mtm.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "mtm.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }


        register("androidTypeSafeNavigation") {
            id = "mtm.android.typesafe.navigation"
            implementationClass = "AndroidTypeSafeNavigationConventionPlugin"
        }

        register("kotlinSerialization") {
            id = "mtm.kotlin.serialization"
            implementationClass = "KotlinSerializationConventionPlugin"
        }

        register("androidKoin") {
            id = "mtm.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }

        register("androidLibrary") {
            id = "mtm.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "mtm.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeature") {
            id = "mtm.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("jvmLibrary") {
            id = "mtm.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("retrofit") {
            id = "mtm.retrofit"
            implementationClass = "RetrofitConventionPlugin"
        }
    }
}
