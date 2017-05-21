package com.leomendez.roomlist.persistence.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * To Do Entity to save it into Database
 */

@Entity( tableName = "todo")
data class ToDo(@PrimaryKey var id:Int = 0,
                @ColumnInfo(name = "task") var toDo:String = "",
                @ColumnInfo(name="todo_date")var date: Date? = null)
