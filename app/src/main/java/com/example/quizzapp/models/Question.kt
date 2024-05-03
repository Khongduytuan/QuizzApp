package com.example.quizzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_question")
class Question(
    @ColumnInfo(name = "name_col") var name: String = "",
    @ColumnInfo(name = "quizset_id_col") var idQuizSet: Int,
    @ColumnInfo(name = "date_created_col") var dateCreated: String = "",
    @ColumnInfo(name = "created_by_col") var createdBy: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id_col")
    var id: Int = 0
}