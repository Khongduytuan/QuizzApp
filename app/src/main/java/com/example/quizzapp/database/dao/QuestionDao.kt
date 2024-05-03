package com.example.quizzapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizzapp.models.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert
    suspend fun insertQuestion(question: Question)
    @Update
    suspend fun updateQuestion(question: Question)
    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("select * from tbl_question")
    fun getAllQuestion(): Flow<List<Question>>

}