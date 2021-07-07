package com.example.projettdmubunto

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@SuppressLint("StaticFieldLeak")
object RoomService {
    private var dataBase: DataBase?=null;
    fun getDatabase(context: Context): DataBase{
        if(dataBase==null){
            dataBase = Room.databaseBuilder(context,DataBase::class.java,"projectTdm44")
                    .allowMainThreadQueries()
                .addCallback(object: RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("TEST", "STARTTTIIIINNNG")
                        Executors.newSingleThreadExecutor().execute {
                            dataBase?.getAyahDo()?.insertAll(Utils.getAllDatabase(context))
                        }
                    }
                })
                .build()
        }
        return dataBase!!
    }

}