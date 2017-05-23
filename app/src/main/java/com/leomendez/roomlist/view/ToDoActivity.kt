package com.leomendez.roomlist.view

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.leomendez.roomlist.R
import com.leomendez.roomlist.repository.ToDoRepository
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

        add_to_do.setOnClickListener { view ->
            ToDoFragment.instance.show(fragmentManager,"ToDoFragment")
        }

    }

    override fun onResume() {
        super.onResume()

        val database = DatabaseManager.invoke<ToDoDatabase>(applicationContext,"todos.db")
        val viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        val repository = ToDoRepository(database)
        viewModel.init(repository)

        viewModel.dataLive.observe(this,Observer{
            list ->  adapter.addItems(list as ArrayList<ToDo>)
        })

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
