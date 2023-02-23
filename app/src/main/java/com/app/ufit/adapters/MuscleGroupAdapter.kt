package com.app.ufit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.ufit.databinding.CardMuscleGroupItemBinding
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.ExercisesDiffUtil

class MuscleGroupAdapter: RecyclerView.Adapter<MuscleGroupAdapter.MyViewHolder>() {

    private var muscleList = emptyList<String>()

    class MyViewHolder(private val binding: CardMuscleGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(muscleGroup : String){
                binding.muscleGroup = muscleGroup
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): MuscleGroupAdapter.MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardMuscleGroupItemBinding.inflate(layoutInflater, parent, false)
                return MuscleGroupAdapter.MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return muscleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val muscleGroupItem = muscleList[position]
        holder.bind(muscleGroupItem)

    }

    fun setData(newData: List<String>){
        muscleList = newData
    }
}