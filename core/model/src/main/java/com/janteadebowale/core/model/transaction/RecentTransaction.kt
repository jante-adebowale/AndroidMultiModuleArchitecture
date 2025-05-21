package com.janteadebowale.core.model.transaction

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

//"transactionType": "Refund",
//"amount": 25000.0,
//"time": "2025-05-18T23:16:18.197452",
//"status": true
data class RecentTransaction(
   val  transactionType :String,
   val  amount :Double,
   val  time :String,
   val  status :Boolean
)
