package com.example.quizzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_account")
class Account(
    @ColumnInfo(name = "username_col") var username: String = "",
    @ColumnInfo(name = "password_col") var password: String = "",
    @ColumnInfo(name = "fullName_col") var fullName: String = "",
    @ColumnInfo(name = "dateCreated_col") var dateCreated: String = "",
    @ColumnInfo(name = "isAdmin_col") var isAdmin: Boolean = false,
    @ColumnInfo(name = "isActive_col") var isActive: Boolean = true
): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "acc_id_col")
    var id: Int = 0
}