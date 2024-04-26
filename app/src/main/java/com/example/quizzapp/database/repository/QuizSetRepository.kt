package com.example.quizzapp.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizzapp.database.QuizSetDatabase
import com.example.quizzapp.database.dao.QuizSetDao
import com.example.quizzapp.models.QuizSet
import kotlinx.coroutines.flow.Flow

class QuizSetRepository(app: Application) {
    private val quizSetDao:QuizSetDao
    init {
        val quizSetDatabase : QuizSetDatabase = QuizSetDatabase.getInstance(app)
        quizSetDao = quizSetDatabase.getQuizSetDao()
    }
    suspend fun insertQuizSet(quizSet: QuizSet) = quizSetDao.insertQuizSet(quizSet)
    suspend fun updateQuizSet(quizSet: QuizSet) = quizSetDao.updateQuizSet(quizSet)
    suspend fun deleteQuizSet(quizSet: QuizSet) = quizSetDao.deleteQuizSet(quizSet)


    fun getAllQuizSet(): Flow<List<QuizSet>> = quizSetDao.getAllQuizSet()
}