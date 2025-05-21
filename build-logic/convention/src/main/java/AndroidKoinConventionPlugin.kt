import com.janteadebowale.convention.addKoinDependencies
import com.janteadebowale.convention.addTypeSafeNavigationDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies{
                addKoinDependencies(target)
            }
        }
    }
}