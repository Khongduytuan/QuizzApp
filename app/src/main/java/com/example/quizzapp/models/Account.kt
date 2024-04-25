package com.example.quizzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_account")
class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "acc_id_col")
    var id: Int = 0
}