package com.leomendez.roomlist.view


import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leomendez.roomlist.R
import com.leomendez.roomlist.repository.ToDoRepository
import com.leomendez.roomlist.persistence.database.database.ToDoDatabase
import com.leomendez.roomlist.persistence.database.entity.ToDo
import com.leomendez.roomlist.persistence.database.manager.DatabaseManager
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_to_do.*
import java.security.SecureRandom
import java.text.DateFormat
import java.util.*


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
        val toDoRepository = ToDoRepository(database)
        to_do_date.setText(dateFormat)

        to_do_date.setOnClickListener {
            showCalendar(Calendar.getInstance(),{
                date ->
                toDoDate = date
                to_do_date.setText(DateFormat.getDateInstance().format(date))
            })
        }

        save_todo_btn.setOnClickListener {
            val id = Math.abs(SecureRandom().nextInt())
            val toDo = ToDo(Math.abs(id),todo_title.text.toString(),toDoDate)
            toDoRepository.insertToDo(database,toDo)
            dismiss()
        }
    }

    private fun showCalendar(calendar:Calendar, callback:(date:Date) -> Unit){
        DatePickerDialog.newInstance({ view, year, monthOfYear, dayOfMonth ->
            calendar.set(year,monthOfYear,dayOfMonth)
            callback(calendar.time)
        }).show(activity.fragmentManager,"date_picker")
    }

}

