package com.app.ufit.bindingadapters

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.app.ufit.ui.fragments.home.HomeFragmentDirections

class MuscleGroupItemBinding {

    companion object {

        @BindingAdapter("onMuscleGroupClickListener")
        @JvmStatic
        fun onMuscleGroupClickListener(muscleGroupLayout: ConstraintLayout, muscle: String){
            muscleGroupLayout.setOnClickListener{
                try{
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToExercisesFragment(muscle)
                    muscleGroupLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d( "onMuscleGroupClickListener", e.toString())
                }
            }
        }

    }
}