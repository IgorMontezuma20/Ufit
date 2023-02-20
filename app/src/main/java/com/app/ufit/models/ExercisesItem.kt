package com.app.ufit.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExercisesItem(
    @SerializedName("difficulty")
    val difficulty: String,
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
