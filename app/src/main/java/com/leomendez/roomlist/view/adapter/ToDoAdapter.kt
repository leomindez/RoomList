package com.leomendez.roomlist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.leomendez.roomlist.R
import com.leomendez.roomlist.persistence.database.entity.ToDo
import mx.leo.easyrecycler.adapter.EasyAdapter

class ToDoAdapter:EasyAdapter<ToDoViewHolder,ToDo>(){

    override fun createHolder(parent: ViewGroup?, viewType: Int): ToDoViewHolder
            = ToDoViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.todo_item_layout,parent,false))

    override fun onBindItemViewHolder(holder: ToDoViewHolder, item: ToDo, position: Int) {
        holder.bindData(item)
    }

}