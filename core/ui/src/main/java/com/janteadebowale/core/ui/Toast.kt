package com.janteadebowale.core.ui

import android.content.Context
import android.widget.Toast

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

fun Context.showToast(message:String, length:Int = Toast.LENGTH_LONG){
    Toast.makeText(this,message,length).show()
}