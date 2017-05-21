package com.leomendez.roomlist.persistence.database.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.leomendez.roomlist.persistence.database.converter.DateTypeConverter
import com.leomendez.roomlist.persistence.database.dao.ToDoDao
import com.leomendez.roomlist.persistence.database.entity.ToDo

/**
 * Create to do Database
 * To see schema look for Entity package
 */
@TypeConverters(DateTypeConverter::class)
@Database(entities = arrayOf(ToDo::class),version = 1)
abstract class ToDoDatabase:RoomDatabase() {
    abstract fun toDoDao():ToDoDao
}

