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


class ExerciseDetailsFragment : Fragment() {

    private var _binding: FragmentExerciseDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var mExerciseDetailsViewModel: ExerciseDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseDetailsBinding.inflate(inflater, container, false)

        mExerciseDetailsViewModel = ViewModelProvider(requireActivity())[ExerciseDetailsViewModel::class.java]

        callComponents()
        requestApiData()

        //binding.ivMuscle.load()


        return binding.root
    }

    private fun requestApiData() {
        Log.d("ExercisesFragment", "request api data called")
        mExerciseDetailsViewModel.getImage(applyQueries())
        mExerciseDetailsViewModel.imageResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    //hideShimmerEffect()
                    response.data?.let { binding.ivMuscle.load(it) }
                }
                is NetworkResult.Error -> {
                    // hideShimmerEffect()
                    //loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    // showShimmerEffect()
                }
            }

        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        val args: ExerciseDetailsFragmentArgs by navArgs()
        val myBundle: ExercisesItem? = args.data


        queries["muscleGroups"] = myBundle?.name as String

        return queries
    }

    private fun callComponents(){

        val args: ExerciseDetailsFragmentArgs by navArgs()
        val myBundle: ExercisesItem? = args.data

        binding.tvExerciseTitle.text = myBundle?.name
        binding.tvMuscleName.text = myBundle?.muscle
        binding.tvDifficultyLevel.text = myBundle?.difficulty
        binding.tvInstructions.text = myBundle?.instructions
    }


}