package com.example.quizzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_answer")
class Answer(
    @ColumnInfo(name = "name_col") var name: String = "",
    @ColumnInfo(name = "id_question_col") var idQuestion: Int,
    @ColumnInfo(name = "is_true_col") var isTrue: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id_col")
    var id: Int = 0
}