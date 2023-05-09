package com.app.ufit.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.ufit.adapters.ExercisesAdapter
import com.app.ufit.adapters.FavoriteAdapter
import com.app.ufit.data.database.ExercisesDatabase
import com.app.ufit.data.database.FavoritesDao
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.databinding.FragmentFavoritesBinding
import com.app.ufit.viewmodels.exercise.ExerciseDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts.getApplication

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var mAdapter: FavoriteAdapter
    private lateinit var mFavoriteDao: FavoritesDao

    private lateinit var exercisesDatabase: ExercisesDatabase
    private lateinit var favoritesList: List<FavoritesEntity>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)


        setupRecyclerView()


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        val db = Room.databaseBuilder(
            requireContext(),
            ExercisesDatabase::class.java, "ExercisesDatabase"
        ).build()
        mFavoriteDao = db.favoritesDao()

        mAdapter = FavoriteAdapter(requireContext(), mFavoriteDao, mutableListOf())
        binding.rvFavoriteExercises.adapter = mAdapter
        binding.rvFavoriteExercises.layoutManager = LinearLayoutManager(requireContext())

        favoritesList = mFavoriteDao.getAllData()}


    private fun setupRecyclerView() {
        binding.rvFavoriteExercises.adapter = mAdapter
        binding.rvFavoriteExercises.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

//    private fun getData() {
//        val list = FavoritesEntity.getDatabase(getApplication()).dao().allData
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter =
//            FavoriteAdapter(applicationContext, mFavoriteDao, list) { position, id ->
//                FavoriteDatabase.getDatabase(applicationContext).dao().deleteData(id)
//                getData()
//            }
//    }

}