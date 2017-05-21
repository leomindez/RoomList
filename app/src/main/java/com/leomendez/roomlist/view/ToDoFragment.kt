package com.leomendez.roomlist.view


import android.app.DialogFragment
import android.os.Bundle
import android.util.Log

import android.view.*

import com.leomendez.roomlist.R
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import com.leomendez.roomlist.persistence.database.manager.DatabaseManager
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_to_do.*
import java.security.SecureRandom
import java.text.DateFormat
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.FutureTask


/**
 * A simple [Fragment] subclass.
 */
class ToDoFragment : DialogFragment() {

    companion object{
        var instance = lazy{ ToDoFragment()}.value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog_Alert);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_to_do, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = Date()
        var toDoDate:Date? = date

        val dateFormat = DateFormat.getDateInstance().format(date)
        val database = DatabaseManager.invoke<ToDoDatabase>(activity.applicationContext,"todos.db")
        to_do_date.setText(dateFormat)

        to_do_date.setOnClickListener {
            showCalendar(Calendar.getInstance(),{
                date ->
                toDoDate = date
                to_do_date.setText(DateFormat.getDateInstance().format(date))
            })
        }

        save_todo_btn.setOnClickListener {
            val id = SecureRandom().nextInt()
            val toDo = ToDo(Math.abs(id),todo_title.text.toString(),toDoDate)
            insertToDo(database,toDo)
            dismiss()
        }
    }

    private fun showCalendar(calendar:Calendar, callback:(date:Date) -> Unit){
        DatePickerDialog.newInstance({ view, year, monthOfYear, dayOfMonth ->
            calendar.set(year,monthOfYear,dayOfMonth)
            callback(calendar.time)
        }).show(activity.fragmentManager,"date_picker")
    }

    private fun insertToDo(database: ToDoDatabase,toDo:ToDo){
        val asyncInsert = FutureTask<Unit>(Callable{
            database.toDoDao().insert(toDo)
        })
        asyncInsert.run()
    }
}

