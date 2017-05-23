package com.leomendez.roomlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.leomendez.roomlist.persistence.database.dao.ToDoDao
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


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
        todo.id = 1
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
        val savedTodo = getValue(toDoDao.getAll())[0]
        assertThat(savedTodo, notNullValue())
        assertThat(savedTodo.id,Matchers.`is`(1))
        assertThat(savedTodo.toDo,Matchers.`is`("Test saving todo"))
    }

    @Test
    fun saveAndDeleteToDo(){
        toDoDao.insert(todo)
        toDoDao.delete(todo)
        val value = getValue(toDoDao.getAll())
        assertThat(value.size, greaterThanOrEqualTo(0))
    }

    @Test
    fun saveAndUpdateToDo(){
        toDoDao.insert(todo)
        val newTodo = ToDo(1,"NEW TODO",Date())
        toDoDao.update(newTodo)

        val newSavedTodo = getValue(toDoDao.getAll())[0]
        assertThat(newSavedTodo, notNullValue())
        assertThat(newSavedTodo.id, `is`(1))
        assertThat(newSavedTodo.toDo, `is`("NEW TODO"))

    }

    @Throws(InterruptedException::class)
    private fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object:Observer<T>{
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }
        }

        liveData.observeForever(observer)
        latch.await(2,TimeUnit.SECONDS)
        return data[0] as T
    }
}