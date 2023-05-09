package com.app.ufit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.ufit.R
import com.app.ufit.data.database.ExercisesDao
import com.app.ufit.databinding.ExerciseItemBinding
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.ExercisesDiffUtil

class ExercisesAdapter : RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>() {


    private var exercise = emptyList<ExercisesItem>()

    class MyViewHolder(private val binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exerciseItem: ExercisesItem) {
            binding.exerciseItem = exerciseItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ExerciseItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return exercise.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentExercise = exercise[position]
        holder.bind(currentExercise)

    }


    fun setData(newData: List<ExercisesItem>){

        val exercisesDiffUtil = ExercisesDiffUtil(exercise, newData)
        val diffUtilResult = DiffUtil.calculateDiff(exercisesDiffUtil)
        exercise = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}