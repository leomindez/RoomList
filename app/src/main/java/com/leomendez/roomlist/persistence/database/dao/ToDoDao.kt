package com.leomendez.roomlist.persistence.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.leomendez.roomlist.persistence.database.entity.ToDo

/**
 * Data Access Object to create query and insert of to do's
 */

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getAll():LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg todo:ToDo)

    @Delete
    fun delete(todo:ToDo)

    @Update
    fun update(vararg todo: ToDo)
}