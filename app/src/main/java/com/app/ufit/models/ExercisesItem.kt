package com.app.ufit.models


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExercisesItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("difficulty")
    val difficulty: String,
//    @SerializedName("equipment")
//    val equipment: String,
    @SerializedName("equipment")
    val equipment: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("muscle")
    val muscle: String,
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("type")
    val type: String

) : Parcelable
