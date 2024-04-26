package com.example.quizzapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quizzapp.models.QuizSet
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizSetDao {
    @Insert
    suspend fun insertQuizSet(quizSet: QuizSet)

    @Update
    suspend fun updateQuizSet(quizSet: QuizSet)

    @Delete
    suspend fun deleteQuizSet(quizSet: QuizSet)

    @Query("select * from tbl_quizset")
    fun getAllQuizSet():Flow<List<QuizSet>>

//    @Query("select * from tbl_quizset where name_col=:name")
//    fun getAllQuizSet(name: String):LiveData<List<QuizSet>>
}