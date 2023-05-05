package com.app.ufit.ui.fragments.exercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.app.ufit.R
import com.app.ufit.databinding.FragmentExerciseDetailsBinding
import com.app.ufit.databinding.FragmentExercisesBinding
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.NetworkResult
import com.app.ufit.viewmodels.exercise.ExerciseDetailsViewModel
import com.app.ufit.viewmodels.favorites.FavoriteExerciseViewModel


class ExerciseDetailsFragment : Fragment() {

    private var _binding: FragmentExerciseDetailsBinding? = null
    private val binding get() = _binding!!
    val args: ExerciseDetailsFragmentArgs by navArgs()
    val myBundle: ExercisesItem? = args.data

    private lateinit var mExerciseDetailsViewModel: ExerciseDetailsViewModel

    private lateinit var mFavoriteExerciseViewModel: FavoriteExerciseViewModel

    private var isFavorite: Boolean = false


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

        binding.favoriteButton.setOnClickListener {

            if (isFavorite) {

                //mFavoriteExerciseViewModel.
                isFavorite = false
                binding.favoriteButton.setImageResource(R.drawable.ic_heart_2)

            } else {
                isFavorite = true
                binding.favoriteButton.setImageResource(R.drawable.ic_heart)

            }
        }

        //binding.ivMuscle.load()


        return binding.root
    }

    private fun requestApiData() {
        mExerciseDetailsViewModel.getImage(myBundle?.muscle as String)
        mExerciseDetailsViewModel.imageResponse.observe(viewLifecycleOwner) { response ->

            binding.ivMuscle.load(response)

        }
    }

//    private fun applyQueries(): HashMap<String, String> {
//        val queries: HashMap<String, String> = HashMap()
//
//
//
//        queries["muscleGroups"] = myBundle?.name as String
//
//        return queries
//    }

    private fun callComponents() {

        binding.tvExerciseTitle.text = myBundle?.name
        binding.tvMuscleName.text = myBundle?.muscle
        binding.tvDifficultyLevel.text = myBundle?.difficulty
        binding.tvInstructions.text = myBundle?.instructions
    }


}