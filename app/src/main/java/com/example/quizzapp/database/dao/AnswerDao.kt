package com.example.quizzapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizzapp.models.Answer
import kotlinx.coroutines.flow.Flow

@Dao
interface AnswerDao {
    @Insert
    suspend fun insertAnswer(answer: Answer)
    @Update
    suspend fun updateAnswer(answer: Answer)
    @Delete
    suspend fun deleteAnswer(answer: Answer)

    @Query("select * from tbl_answer")
    fun getAllAnswer(): Flow<List<Answer>>
}