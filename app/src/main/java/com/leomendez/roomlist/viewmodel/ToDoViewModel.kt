package com.leomendez.roomlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.leomendez.roomlist.repository.ToDoRepository
import com.leomendez.roomlist.persistence.database.entity.ToDo

/**
 * Created by leomendez on 20/05/17.
 */

class ToDoViewModel:ViewModel(){
    private var toDoRepository: ToDoRepository? = null
    var dataLive:LiveData<List<ToDo>> = MutableLiveData<List<ToDo>>()


    fun init(toDoRepository: ToDoRepository){
        dataLive = toDoRepository.getAllToDO()
    }


}