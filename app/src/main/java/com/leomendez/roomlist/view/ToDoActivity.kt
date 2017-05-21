package com.leomendez.roomlist.view

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.leomendez.roomlist.R
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import com.leomendez.roomlist.persistence.database.manager.DatabaseManager
import com.leomendez.roomlist.view.adapter.ToDoAdapter
import com.leomendez.roomlist.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_to_do.*
import kotlinx.android.synthetic.main.content_to_do.*
import java.util.*

class ToDoActivity : LifecycleActivity() {


    private var adapter:ToDoAdapter

    init{
        adapter = ToDoAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)



        todo_list.layoutManager = LinearLayoutManager(this)
        todo_list.setHasFixedSize(true)
        todo_list.adapter = adapter
        val database = DatabaseManager.invoke<ToDoDatabase>(applicationContext,"todos.db")
        Log.d("Today", Date().time.toString())
        val viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        viewModel.init(database)

        viewModel.getAllToDo()?.observe(this, Observer{
            list ->
            adapter.addItems(list as ArrayList<ToDo>)
        })

        add_to_do.setOnClickListener { view ->
            ToDoFragment.instance.show(fragmentManager,"ToDoFragment")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_to_do, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
