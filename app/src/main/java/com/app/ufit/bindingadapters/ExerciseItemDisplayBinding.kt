package com.app.ufit.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.app.ufit.R
import com.app.ufit.models.ExercisesItem
import com.app.ufit.ui.fragments.exercise.ExercisesFragmentDirections
import com.app.ufit.ui.fragments.home.HomeFragmentDirections

class ExerciseItemDisplayBinding {

    companion object {

        @BindingAdapter("onExerciseItemClickListener")
        @JvmStatic
        fun onExerciseItemClickListener(exerciseItem: CardView, exercise: ExercisesItem) {
            exerciseItem.setOnClickListener {
                try {
                    val action =
                        ExercisesFragmentDirections.actionExercisesFragmentToExerciseDetailsFragment(exercise)
                    exerciseItem.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onMuscleGroupClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                //error(R.drawable.ic_error_placeholder)
            }
        }


    }
}