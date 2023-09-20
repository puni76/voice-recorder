package com.example.recorderjetpackcompose.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppUtils {
    fun getDate(date: Long): String {
//        var tempDate = date
//        tempDate *= 1000L
        return SimpleDateFormat(AppConstants.DATE_FORMAT, Locale.getDefault()).format(
            Date(
                date
            )
        )
    }
}