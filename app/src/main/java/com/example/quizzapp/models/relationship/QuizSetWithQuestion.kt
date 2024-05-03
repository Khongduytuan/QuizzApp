package com.example.quizzapp.models.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.quizzapp.models.Question
import com.example.quizzapp.models.QuizSet

data class QuizSetWithQuestion(
    @Embedded val quizSet: QuizSet,
    @Relation(
        parentColumn = "quizset_id_col",
        entityColumn = "quizset_id_col"
    )
    val question: List<Question>
)
