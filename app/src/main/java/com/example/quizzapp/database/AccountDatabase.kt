package com.example.quizzapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizzapp.database.dao.AccountDao
import com.example.quizzapp.models.Account

@Database(entities = [Account::class], version = 1)
abstract class AccountDatabase: RoomDatabase() {
    abstract fun getAccountDao(): AccountDao

    companion object{
        @Volatile
        private var instance: AccountDatabase? = null
        fun getInstance(context: Context): AccountDatabase {
            if (AccountDatabase.instance == null) {
                AccountDatabase.instance = Room.databaseBuilder(context, AccountDatabase::class.java, "AccountDatabase")
                    .build()
            }
            return AccountDatabase.instance!!
        }
    }

}