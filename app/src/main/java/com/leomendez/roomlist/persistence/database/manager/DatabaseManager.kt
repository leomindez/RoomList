package com.leomendez.roomlist.persistence.database.manager

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
class DatabaseManager{
    companion object{
        inline operator fun <reified T:RoomDatabase> invoke(context:Context,database:String):T{
            return lazy { Room.databaseBuilder(context,T::class.java,database).build() }.value
        }
    }
}