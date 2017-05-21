package com.leomendez.roomlist

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.leomendez.roomlist.persistence.database.dao.ToDoDao
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import org.hamcrest.Matchers
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class DatabaseTest{

    private lateinit var todoDao:ToDoDao
    private lateinit var todoDatabase:ToDoDatabase
    private lateinit var toDo:ToDo
    @Before
    fun before(){
        val instrumentation = InstrumentationRegistry.getTargetContext()
        todoDatabase = Room.inMemoryDatabaseBuilder(instrumentation.applicationContext,ToDoDatabase::class.java).build()
        todoDao = todoDatabase.toDoDao()
        toDo = ToDo()
        toDo.id = 0
        toDo.toDo = "Testing Saved "
        toDo.date = Date()
    }


    @After
    fun after(){
        todoDatabase.close()
    }

    @Test
    fun saveAndReadToDo() {
        todoDao.insert(toDo)
        val savedTodo = todoDao.getToDoList().get(0)
        assertThat(savedTodo, equalTo(toDo))
    }

    @Test
    fun saveAndDeleteToDo(){
        todoDao.insert(toDo)
        todoDao.deleteToDo(toDo)
        assertThat(todoDao.getToDoList(),hasSize(Matchers.greaterThanOrEqualTo(0)))
    }

    @Test
    fun saveAndUpdateToDo(){
        todoDao.insert(toDo)
        val newTodo = ToDo(0,"Update todo",Date())
        todoDao.updateToDo(newTodo)
        assertThat(newTodo, equalTo(todoDao.getToDoList()[0]))
    }
}
