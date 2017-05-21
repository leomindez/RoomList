package com.leomendez.roomlist.persistence.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by leomendez on 20/05/17.
 */
class DateTypeConverter {
        @TypeConverter
        fun fromTimestamp(value:Long): Date?  = Date(value)
        @TypeConverter
        fun dateToTimestamp(date:Date): Long? = date.time
}