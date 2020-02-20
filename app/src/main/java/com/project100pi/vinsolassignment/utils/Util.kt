package com.project100pi.vinsolassignment.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Sachin Verma on 2019-12-08.
 */

object Util {

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.format(calendar.time)
        return date
    }

    fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("HH:mm")
        val date = dateFormat.format(calendar.time)
        return date
    }

    fun getExtraHourTime(): String {
        val calendar = Calendar.getInstance()
        calendar.set(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY) + 1,
            calendar.get(Calendar.MINUTE))
        val dateFormat = SimpleDateFormat("HH:mm")
        val date = dateFormat.format(calendar.time)
        return date
    }

}