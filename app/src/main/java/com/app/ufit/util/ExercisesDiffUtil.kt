package com.app.ufit.util

import androidx.recyclerview.widget.DiffUtil
import com.app.ufit.models.ExercisesItem

class ExercisesDiffUtil(
    private val oldList: List<ExercisesItem>,
    private val newList: List<ExercisesItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}