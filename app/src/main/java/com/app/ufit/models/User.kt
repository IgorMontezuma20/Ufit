package com.app.ufit.models

import android.os.Parcelable
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("session_token") val sessionToken: String? = null,
    @SerializedName("isAvailable") val isAvailable: Boolean? = null

) : Parcelable {

    override fun toString(): String {
        return "User(id=$id, name='$name', lastName='$lastName', gender='$gender', email='$email', " +
                "password='$password', image=$image, " +
                "sessionToken=$sessionToken, isAvailable=$isAvailable)"
    }
}

