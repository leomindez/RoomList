package com.leomendez.roomlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo

/**
 * Created by leomendez on 20/05/17.
 */

class ToDoViewModel:ViewModel(){

    private var database:ToDoDatabase? = null

    fun init(database: ToDoDatabase){
        this.database = database
    }

    fun getAllToDo():LiveData<List<ToDo>>?
        = database?.toDoDao()?.getAll()

}