package com.app.ufit.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ufit.R
import com.app.ufit.adapters.MuscleGroupAdapter
import com.app.ufit.databinding.FragmentHomeBinding
import com.app.ufit.viewmodels.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy {MuscleGroupAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding  = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()


        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);



        val listMuscleGroup = arrayListOf<String>()

        listMuscleGroup.add(getString(R.string.abdominals))
        listMuscleGroup.add(getString(R.string.abductors))
        listMuscleGroup.add(getString(R.string.adductors))
        listMuscleGroup.add(getString(R.string.biceps))
        listMuscleGroup.add(getString(R.string.calves))
        listMuscleGroup.add(getString(R.string.chest))
        listMuscleGroup.add(getString(R.string.forearms))
        listMuscleGroup.add(getString(R.string.glutes))
        listMuscleGroup.add(getString(R.string.hamstrings))
        listMuscleGroup.add(getString(R.string.lats))
        listMuscleGroup.add(getString(R.string.lower_back))
        listMuscleGroup.add(getString(R.string.middle_back))
        listMuscleGroup.add(getString(R.string.neck))
        listMuscleGroup.add(getString(R.string.quadriceps))
        listMuscleGroup.add(getString(R.string.traps))
        listMuscleGroup.add(getString(R.string.triceps))

        mAdapter.setData(listMuscleGroup)
    }


}