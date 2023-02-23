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
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ufit.adapters.ExercisesAdapter
import com.app.ufit.databinding.FragmentExercisesBinding
import com.app.ufit.ui.fragments.home.HomeFragment
import com.app.ufit.util.NetworkResult
import com.app.ufit.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

        return binding.root
    }

    private fun requestApiData() {
        Log.d("ExercisesFragment", "request api data called")
        mainViewModel.getExercises(applyQueries())
        mainViewModel.exercisesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    //hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                    Log.d("listSize", response.data?.size.toString())
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
        var exercise = args.muscle as String

        if (exercise.equals("Lower back")) {
            exercise = "lower_back"
        } else if (exercise.equals("Middle back")) {
            exercise = "middle_back"
        }
        queries["muscle"] = exercise.lowercase()

        return queries
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

//    private fun showShimmerEffect() {
//        binding.shimmerFrameLayout.startShimmer()
//        binding.shimmerFrameLayout.visibility = View.VISIBLE
//        binding.recyclerview.visibility = View.GONE
//
//    }
//
//    private fun hideShimmerEffect() {
//        binding.shimmerFrameLayout.stopShimmer()
//        binding.shimmerFrameLayout.visibility = View.GONE
//        binding.recyclerview.visibility = View.VISIBLE
//    }

}