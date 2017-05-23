package com.leomendez.roomlist.repository

import android.arch.lifecycle.LiveData
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import java.util.concurrent.Executors

/**
 * Created by mendezl on 23/05/2017.
 */
class ToDoRepository(private var database: ToDoDatabase) {

    fun getAllToDO(): LiveData<List<ToDo>>
         = database.toDoDao().getAll()


     fun insertToDo(database: ToDoDatabase, toDo: ToDo){
        Executors.newFixedThreadPool(3).execute {
            try {
                database.toDoDao().insert(toDo)
            }finally {
                database.close()
            }
        }
    }
}