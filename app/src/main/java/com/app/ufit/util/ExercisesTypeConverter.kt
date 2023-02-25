package com.app.ufit.util

import androidx.room.TypeConverter
import com.app.ufit.models.Exercises
import com.app.ufit.models.ExercisesItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExercisesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun ExercisesToString(exercises: Exercises) : String{
        return gson.toJson(exercises)
    }

    fun stringToExercise(data: String): Exercises {
        val listType = object : TypeToken<ExercisesItem>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(exercisesItem: ExercisesItem): String {
        return gson.toJson(exercisesItem)
    }

    @TypeConverter
    fun stringToResult(data: String): ExercisesItem {
        val listType = object : TypeToken<ExercisesItem>() {}.type
        return gson.fromJson(data, listType)
    }

}