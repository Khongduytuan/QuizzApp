package com.example.quizzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_quizset")
data class QuizSet(@ColumnInfo(name = "name_col") var name: String = "",
              @ColumnInfo(name = "process_col") var process: String = "",
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quizset_id_col")
    var id: Int = 0)
 : Serializable