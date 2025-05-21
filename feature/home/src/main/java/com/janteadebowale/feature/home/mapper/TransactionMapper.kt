package com.janteadebowale.feature.home.mapper

import androidx.compose.ui.graphics.Color
import com.janteadebowale.core.model.transaction.RecentTransaction
import com.janteadebowale.feature.home.model.TransactionUi
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

fun RecentTransaction.toTransactionUi(): TransactionUi {

    val trimmedInput = time.substringBefore(".")
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a")

    val dateTime = LocalDateTime.parse(trimmedInput, inputFormatter)


    val nairaFormatter = NumberFormat.getCurrencyInstance(Locale("en", "NG"))

    return TransactionUi(
        type = transactionType,
        amount = nairaFormatter.format(amount),
        time = outputFormatter.format(dateTime),
        color = if (status) Color.Green else Color.Red
    )
}