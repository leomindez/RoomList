package com.leomendez.roomlist.persistence.database.dao

import android.arch.persistence.room.*
import com.leomendez.roomlist.persistence.database.entity.ToDo

/**
 * Data Access Object to create query and insert of to do's
 */

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo")
    fun getToDoList():List<ToDo>

    @Insert
    fun insert(vararg todo:ToDo)

    @Delete
    fun deleteToDo(todo:ToDo)

    @Update
    fun updateToDo(vararg todo: ToDo)
}