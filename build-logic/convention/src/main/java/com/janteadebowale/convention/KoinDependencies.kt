package com.janteadebowale.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

fun DependencyHandlerScope.addKoinDependencies(project: Project) {
    "implementation"(project.libs.findBundle("koin").get())
}