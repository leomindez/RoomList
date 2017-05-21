package com.leomendez.roomlist.persistence.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.leomendez.roomlist.persistence.database.converter.DateTypeConverter
import java.util.*

/**
 * To Do Entity to save it into Database
 */

@TypeConverters(DateTypeConverter::class)
@Entity
data class ToDo(@PrimaryKey var id:Int = 0,
                @ColumnInfo(name = "todo") var toDo:String = "",
                @ColumnInfo(name="todo_date")var date: Date? = null)
