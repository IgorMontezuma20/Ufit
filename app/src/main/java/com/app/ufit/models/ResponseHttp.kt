package com.app.ufit.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ResponseHttp (

    @SerializedName("message") val message :String,
    @SerializedName("success") val isSuccess : Boolean,
    @SerializedName("data") val code : JsonObject,
    @SerializedName("error") val error : String


){

    override fun toString(): String {
        return "ResponseHttp(message='$message', isSuccess=$isSuccess, code=$code, error='$error')"
    }
}