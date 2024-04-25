package com.example.quizzapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizzapp.database.dao.QuizSetDao
import com.example.quizzapp.models.QuizSet

@Database(entities = [QuizSet::class], version = 1)
abstract class QuizSetDatabase : RoomDatabase() {
    abstract fun getQuizSetDao(): QuizSetDao

    companion object {
        @Volatile
        private var instance: QuizSetDatabase? = null

        fun getInstance(context: Context): QuizSetDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, QuizSetDatabase::class.java, "QuizSetDatabase")
                        .build()
            }
            return instance!!
        }
        // DJ - Hilt - Koin


    }
}