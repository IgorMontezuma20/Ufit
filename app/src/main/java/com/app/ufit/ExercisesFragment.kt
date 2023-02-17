package com.app.ufit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ufit.adapters.ExercisesAdapter
import com.app.ufit.databinding.FragmentExercisesBinding
import com.app.ufit.util.Constants.Companion.API_KEY
import com.app.ufit.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ExercisesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!


    private val mAdapter by lazy { ExercisesAdapter() }

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

        //mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupRecyclerView()
        requestApiData()

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

//        queries["number"] = "10"
//        queries["x-api-key"] = API_KEY

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