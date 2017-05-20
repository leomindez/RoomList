package com.leomendez.roomlist.persistence.database.manager

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

inline fun <reified T:RoomDatabase> Room.create(context: Context, databaseName:String):Lazy<T>
        = lazy {Room.databaseBuilder(context,T::class.java,databaseName).build()}
