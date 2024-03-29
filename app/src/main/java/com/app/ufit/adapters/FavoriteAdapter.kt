package com.app.ufit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.ufit.ClickEvent
import com.app.ufit.R
import com.app.ufit.data.database.FavoritesDao
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.databinding.ExerciseItemBinding
import com.app.ufit.models.ExercisesItem

class FavoriteAdapter(
    private val context: Context,
    private val favoriteDao: FavoritesDao,
    private val clickEvent: ClickEvent,
    private val list: MutableList<FavoritesEntity>
    //private val deleteItemClickListener: DeleteItemClickListner
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        holder.title.text = model.name
        holder.description.text = model.instructions
        holder.muscle.text = model.muscle
        holder.difficulty.text = model.difficulty

        holder.itemView.setOnClickListener {
            clickEvent.OnClick(position)
        }

        holder.itemView.setOnLongClickListener {

            clickEvent.OnLongPress(position)
            true
        }

//        val isFavorite = favoriteDao.isFavorite(model.id)
//        if (isFavorite) {
//            holder.iv_favorite.setImageResource(R.drawable.ic_favorite)
//        } else {
//            holder.iv_favorite.setImageResource(R.drawable.ic_favorite_border)
//        }

//        holder.iv_favorite.setOnClickListener {
//            deleteItemClickListener.onItemDelete(position, list[position].id)
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tvName)
        var description: TextView = view.findViewById(R.id.tvDesc)
        var muscle: TextView = view.findViewById(R.id.tvMuscle)
        var difficulty: TextView = view.findViewById(R.id.tvDifficulty)
        //var iv_favorite: ImageView = view.findViewById(R.id.iv_favorite)
    }


    fun updateList(newList: List<FavoritesEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    interface DeleteItemClickListner {
        fun onItemDelete(position: Int, id: Int)
    }

}