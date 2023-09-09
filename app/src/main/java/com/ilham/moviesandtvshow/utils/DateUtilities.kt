package com.ilham.moviesandtvshow.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtilities {
    companion object {
        fun getStringDate(date: String): String {
            //String date_ = date;
            val dateArray = date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            try {
                val mDate = sdf.parse(date)
                val timeInMilliseconds = mDate.time
                val dateString = formatDate(timeInMilliseconds).split(" ".toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()
                return dateString[0].substring(0, 3) + " " + dateString[1] + ", " + dateArray[0]
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return ""
        }

        fun formatDate(timeInMillis: Long): String {
            val dateFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
            return dateFormat.format(timeInMillis)
        }

        fun formatTime(timeInMillis: Long): String {
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            return dateFormat.format(timeInMillis)
        }

        fun isYesterday(timeInMillis: Long): Boolean {
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val date = dateFormat.format(timeInMillis)
            return date == dateFormat.format(System.currentTimeMillis() - 86400000)
        }

        fun isToday(timeInMillis: Long): Boolean {
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val date = dateFormat.format(timeInMillis)
            return date == dateFormat.format(System.currentTimeMillis())
        }

        fun getDateFromEpoch(epoch: Long): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date(epoch))
        }

        fun getYear(timeInMillis: Long): String {
            val dateFormat = SimpleDateFormat("YYYY", Locale.getDefault())
            return dateFormat.format(timeInMillis)!!
        }

    }
}