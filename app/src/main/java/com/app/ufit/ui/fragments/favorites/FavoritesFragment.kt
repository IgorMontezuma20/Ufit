package com.app.ufit.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.ufit.adapters.FavoriteAdapter
import com.app.ufit.data.database.ExercisesDatabase
import com.app.ufit.data.database.FavoritesDao
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

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

        mAdapter = FavoriteAdapter(requireContext(), mFavoriteDao, mutableListOf())
        binding.rvFavoriteExercises.adapter = mAdapter
        binding.rvFavoriteExercises.layoutManager = LinearLayoutManager(requireContext())

        // Perform database operation in a background thread using Kotlin Coroutines
        lifecycleScope.launch {
            favoritesList = withContext(Dispatchers.IO) {
                mFavoriteDao.getAllData()
            }
            mAdapter.updateList(favoritesList)
        }
    }
    }





