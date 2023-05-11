package com.app.ufit.ui.fragments.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ufit.adapters.ExercisesAdapter
import com.app.ufit.databinding.FragmentExercisesBinding
import com.app.ufit.util.NetworkResult
import com.app.ufit.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ExercisesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { ExercisesAdapter() }

    private val args: ExercisesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)

        requestApiData()
        setupRecyclerView()
        readDatabase()

        return binding.root
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readExercises.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty() && !args.backFromBottomSheet) {
                    Log.d("RecipesFragment", "readDatabase called")
                    mAdapter.setData(database.first().ExercisesItem)
                } else {
                    requestApiData()
                }
            }
        }
    }

    private fun requestApiData() {
        Log.d("ExercisesFragment", "request api data called")
        mainViewModel.getExercises(applyQueries())
        mainViewModel.exercisesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                    Log.d("listSize", response.data?.size.toString())
                }

                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                }
            }

        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        var exercise = args.muscle as String

        val listExercisetypes = arrayListOf<String>()
        listExercisetypes.add("cardio")
        listExercisetypes.add("olympic weightlifting")
        listExercisetypes.add("plyometrics")
        listExercisetypes.add("powerlifting")
        listExercisetypes.add("strength")
        listExercisetypes.add("stretching")
        listExercisetypes.add("strongman")

        var verify = false
        for (exerciseType in listExercisetypes) {
            if (exercise.equals(exerciseType)) {
                verify = true
                break
            }
        }

        if (verify) {
            if (exercise.equals("olympic weightlifting")){
                exercise = "olympic_weightlifting"
            }
            queries["type"] = exercise.lowercase()

        } else {
            if (exercise.equals("Lower back")) {
                exercise = "lower_back"
            } else if (exercise.equals("Middle back")) {
                exercise = "middle_back"
            }
            queries["muscle"] = exercise.lowercase()
        }



        return queries
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}