package com.leomendez.roomlist.view.adapter

import android.view.View
import com.leomendez.roomlist.persistence.database.entity.ToDo
import kotlinx.android.synthetic.main.todo_item_layout.view.*
import mx.leo.easyrecycler.viewholder.EasyItemViewHolder
import java.text.DateFormat

/**
 * Created by leomendez on 20/05/17.
 */
class ToDoViewHolder(view: View):EasyItemViewHolder(view) {

    fun bindData(toDo:ToDo){
        itemView.todo_date.setText(DateFormat.getDateInstance().format(toDo.date))
        itemView.todo.setText(toDo.toDo)
    }
}