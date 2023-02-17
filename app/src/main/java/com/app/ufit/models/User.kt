package com.app.ufit.models

import com.google.android.material.textfield.TextInputEditText
import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("lastname") val lastName: String?  = null,
    @SerializedName("email") val email: String?  = null,
    @SerializedName("password") val password: String?  = null,
    @SerializedName("image") val image: String? = null ,
    @SerializedName("session_token") val sessionToken: String? = null,
    @SerializedName("isAvailable") val isAvailable: Boolean? = null

) {

    override fun toString(): String {
        return "User(id='$id', name='$name', lastName='$lastName', email='$email'," +
                " address='$password', image='$image', " +
                "sessionToken='$sessionToken', isAvailable=$isAvailable)"
    }
}

