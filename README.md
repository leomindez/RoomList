# RoomList
How to use Room Library to persistence data into Android Application 

There are 3 main classes that I used to create the example.

[ToDoViewModel](https://github.com/leomindez/RoomList/blob/master/app/src/main/java/com/leomendez/roomlist/viewmodel/ToDoViewModel.kt)
It helps to show the data from repository class. 
Live Data uses Observable/Observer to notify data changes. 

**Create new executors to insert/delete/update objects into database using Room**.

[ToDoRepository](https://github.com/leomindez/RoomList/blob/master/app/src/main/java/com/leomendez/roomlist/repository/ToDoRepository.kt)
Get and insert data. Repository uses LiveData to notify by using changes to observer objects. 


[Room Database](https://github.com/leomindez/RoomList/blob/master/app/src/main/java/com/leomendez/roomlist/persistence/database/database/ToDoDatabase.kt)

Persistence Implementation by using Room Android Library


## LiveData
```
    var dataLive:LiveData<List<ToDo>> = MutableLiveData<List<ToDo>>()
    
```

## ViewModel 
```
        val viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        viewModel.dataLive.observe(this,Observer{
            list ->  adapter.addItems(list as ArrayList<ToDo>)
        })
```

## Database 

```
@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getAll():LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg todo:ToDo)

    @Delete
    fun delete(todo:ToDo)

    @Update
    fun update(vararg todo: ToDo)
}

@Database(entities = arrayOf(ToDo::class),version = 1)
abstract class ToDoDatabase:RoomDatabase() {
    abstract fun toDoDao():ToDoDao
}

```
