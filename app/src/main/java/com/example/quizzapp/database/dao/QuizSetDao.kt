package com.example.quizzapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quizzapp.models.Question
import com.example.quizzapp.models.QuizSet
import com.example.quizzapp.models.relationship.QuizSetWithQuestion
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

    // Insert câu hỏi có khóa phụ là id của QuizSet
    @Insert
    suspend fun insertQuestion(question: Question)

    // Lấy ra các question có khóa phụ là id của QuizSet
    @Transaction
    @Query("select * from tbl_quizset where quizset_id_col = :idQuizSet")
    fun getQuestionByIDQuizSet(idQuizSet: Int): Flow<List<QuizSetWithQuestion>>
}