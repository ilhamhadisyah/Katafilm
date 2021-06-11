package com.ilham.moviesandtvshow.data.source.local.room

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun intToString(list: ArrayList<Int>?): String {
        return list?.joinToString(separator = ";") { it.toString() } ?: ""
    }

    @TypeConverter
    fun toArrayListOfInt(string: String?): ArrayList<Int> {
        return ArrayList(string?.split(";")?.mapNotNull { it.toIntOrNull() } ?: emptyList())
    }
}