package com.leomendez.roomlist

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.leomendez.roomlist.persistence.database.dao.ToDoDao
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by leomendez on 20/05/17.
 */

@RunWith(AndroidJUnit4::class)
class ToDoDatabaseTest {

    private lateinit var database:ToDoDatabase
    private lateinit var toDoDao:ToDoDao
    private lateinit var todo:ToDo

    @Before
    fun setup(){

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        database = Room.inMemoryDatabaseBuilder(instrumentation.context,ToDoDatabase::class.java).build()
        toDoDao = database.toDoDao()
        todo = ToDo()
        todo.id = 0
        todo.date = Date()
        todo.toDo = "Test saving todo"
    }

    @After
    fun finish(){
        database.close()
    }

    @Test
    fun saveAndGetToDo(){
        toDoDao.insert(todo)
        assertThat(todo,equalTo(toDoDao.getAll().get(0)))
    }

    @Test
    fun saveAndDeleteToDo(){
        toDoDao.insert(todo)
        toDoDao.delete(todo)
        assertThat(toDoDao.getAll().size, Matchers.greaterThanOrEqualTo(0))
    }

    @Test
    fun saveAndUpdateToDo(){
        toDoDao.insert(todo)
        val newTodo = ToDo(0,"NEW TODO",Date())
        toDoDao.update(newTodo)
        assertThat(newTodo, equalTo(newTodo))
    }
}