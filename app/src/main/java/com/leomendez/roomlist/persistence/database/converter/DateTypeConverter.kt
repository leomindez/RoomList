package com.leomendez.roomlist.persistence.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun dateToLong(date: Date):Long? = date.time

    @TypeConverter
    fun longToDate(value:Long): Date? = Date(value)
}