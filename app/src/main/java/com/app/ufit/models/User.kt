package com.app.ufit.models

import android.os.Parcelable
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.*


@Parcelize
data class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("birthdate") var birthDate: Date,
    @SerializedName("weight") var weight: String,
    @SerializedName("height") var height: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("session_token") val sessionToken: String? = null,
    @SerializedName("isAvailable") val isAvailable: Boolean? = null

) : Parcelable {
    override fun toString(): String {
        return "User(id=$id, name='$name', lastName='$lastName', gender='$gender', birthDate=$birthDate, weight='$weight', height='$height', email='$email', password='$password', image=$image, sessionToken=$sessionToken, isAvailable=$isAvailable)"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

}

