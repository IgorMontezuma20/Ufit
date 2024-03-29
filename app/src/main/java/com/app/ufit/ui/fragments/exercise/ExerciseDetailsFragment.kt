package com.app.ufit.ui.fragments.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import coil.load
import com.app.ufit.R
import com.app.ufit.data.database.ExercisesDatabase
import com.app.ufit.data.database.FavoritesDao
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.databinding.FragmentExerciseDetailsBinding
import com.app.ufit.models.ExercisesItem
import com.app.ufit.viewmodels.exercise.ExerciseDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ExerciseDetailsFragment : Fragment() {

    private var _binding: FragmentExerciseDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var mExerciseDetailsViewModel: ExerciseDetailsViewModel

    private lateinit var favoriteDao: FavoritesDao

    private lateinit var mFavorite: FavoritesEntity

    private lateinit var favoritesList: List<FavoritesEntity>

    private var isFavorite = false

    val args: ExerciseDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseDetailsBinding.inflate(inflater, container, false)

        mExerciseDetailsViewModel =
            ViewModelProvider(requireActivity())[ExerciseDetailsViewModel::class.java]

        callComponents()
        requestApiData()

        val db = Room.databaseBuilder(
            requireContext(),
            ExercisesDatabase::class.java, "ExercisesDatabase"
        ).build()
        favoriteDao = db.favoritesDao()

        getFavoritesList()

        binding.ivFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                addToFavorites()
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_border)
                removeFromFavorites()
            }
        }

        return binding.root
    }

    private fun requestApiData() {
        val myBundle: ExercisesItem? = args.data

        if (myBundle != null) {
            mExerciseDetailsViewModel.getImage(myBundle?.muscle as String)
            mExerciseDetailsViewModel.imageResponse.observe(viewLifecycleOwner) { response ->
                binding.ivMuscle.load(response)
            }
        }
    }

    private fun callComponents() {
        val myBundle: ExercisesItem? = args.data

        binding.tvExerciseTitle.text = myBundle?.name
        binding.tvMuscleName.text = myBundle?.muscle
        binding.tvDifficultyLevel.text = myBundle?.difficulty
        binding.tvInstructions.text = myBundle?.instructions

        if (myBundle != null) {
            mFavorite = FavoritesEntity(
                id = myBundle!!.id,
                name = myBundle?.name ?: "",
                difficulty = myBundle?.difficulty ?: "",
                equipment = myBundle?.equipment ?: "",
                instructions = myBundle?.instructions ?: "",
                muscle = myBundle?.muscle ?: "",
                type = myBundle?.type ?: ""
            )
        }
    }


    private fun addToFavorites() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                favoriteDao.insertFavorite(mFavorite)
            }
        }
    }


    private fun removeFromFavorites() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val favorite = favoriteDao.findFavoriteByName(mFavorite.name)
                favorite?.let {
                    favoriteDao.deleteFavorite(it.id)
                    Log.d(
                        "ExerciseDetailsFragment",
                        "Exercise removed from favorites with id = ${it.id}"
                    )
                }
            }
        }
    }

    private fun getFavoritesList() {
        lifecycleScope.launch {
            favoritesList = withContext(Dispatchers.IO) {
                favoriteDao.getAllData()
            }
            verifyFavorite()
        }

    }

    private fun verifyFavorite() {
        val myBundle: ExercisesItem? = args.data

        if (myBundle != null) {
            for (favoriteItem in favoritesList) {
                if (favoriteItem.name.equals(myBundle.name)) {
                    binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                    isFavorite = true
                    return
                }
            }
        }
    }
}