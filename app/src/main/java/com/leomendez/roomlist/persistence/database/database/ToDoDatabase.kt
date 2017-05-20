package com.leomendez.roomlist.persistence.database.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.leomendez.roomlist.persistence.database.dao.ToDoDao
import com.leomendez.roomlist.persistence.database.entity.ToDo

/**
 * Create to do Database
 * To see schema look for Entity package
 */

@Database(entities = arrayOf(ToDo::class),version = 1)
abstract class ToDoDatabase:RoomDatabase() {
    abstract fun toDoDao():ToDoDao
}
