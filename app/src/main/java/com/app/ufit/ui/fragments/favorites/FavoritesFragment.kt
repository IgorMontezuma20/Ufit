package com.app.ufit.ui.fragments.favorites

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.ufit.ClickEvent
import com.app.ufit.R
import com.app.ufit.adapters.FavoriteAdapter
import com.app.ufit.data.database.ExercisesDatabase
import com.app.ufit.data.database.FavoritesDao
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.databinding.CustomExitDialogBinding
import com.app.ufit.databinding.FragmentFavoritesBinding
import com.app.ufit.models.ExercisesItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoritesFragment : Fragment(), ClickEvent {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var mAdapter: FavoriteAdapter
    private lateinit var mFavoriteDao: FavoritesDao
    private lateinit var favoritesList: List<FavoritesEntity>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireContext(),
            ExercisesDatabase::class.java, "ExercisesDatabase"
        ).build()
        mFavoriteDao = db.favoritesDao()

        setupRecycler()

    }


    override fun OnClick(position: Int) {
        val favEntity = favoritesList.get(position)
        val exercise = ExercisesItem(
            favEntity.id,
            favEntity.difficulty,
            favEntity.equipment,
            favEntity.instructions,
            favEntity.muscle,
            favEntity.name,
            favEntity.type
        )
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToExerciseDetailsFragment(exercise)
        findNavController().navigate(action)
    }

    override fun OnLongPress(pos: Int) {
        openDeleteDialog(pos)
    }

    private fun openDeleteDialog(pos: Int) {
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.custom_delete_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(view)
        val dialogBinding = CustomExitDialogBinding.bind(view)
        val confirmButton = dialogBinding.confirmButton
        val cancelButton = dialogBinding.cancelButton

        val mAlertDialog = dialog.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }

        confirmButton.setOnClickListener {
            removeFromFavorites(pos)
            setupRecycler()
            mAlertDialog.cancel()
        }
        cancelButton.setOnClickListener {
            mAlertDialog.cancel()
        }

    }
    private fun setupRecycler() {
        mAdapter = FavoriteAdapter(requireContext(), mFavoriteDao, this, mutableListOf())
        binding.rvFavoriteExercises.adapter = mAdapter
        binding.rvFavoriteExercises.layoutManager = LinearLayoutManager(requireContext())

        // Perform database operation in a background thread using Kotlin Coroutines
        lifecycleScope.launch {
            favoritesList = withContext(Dispatchers.IO) {
                mFavoriteDao.getAllData()
            }
            if (favoritesList.size > 0) {
                mAdapter.updateList(favoritesList)
                binding.emptyImage.visibility = View.INVISIBLE
                binding.tvFavorites.visibility = View.INVISIBLE
                binding.rvFavoriteExercises.visibility = View.VISIBLE
            } else {
                binding.emptyImage.visibility = View.VISIBLE
                binding.tvFavorites.visibility = View.VISIBLE
                binding.rvFavoriteExercises.visibility = View.INVISIBLE
            }
        }
    }

    private fun removeFromFavorites(pos : Int) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val favorite = mFavoriteDao.findFavoriteByName(favoritesList.get(pos).name)
                favorite?.let {
                    mFavoriteDao.deleteFavorite(it.id)
                    Log.d(
                        "ExerciseDetailsFragment",
                        "Exercise removed from favorites with id = ${it.id}"
                    )
                }
            }
        }
    }

}







