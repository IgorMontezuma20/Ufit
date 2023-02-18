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

        listMuscleGroup.add("abdominal")
        listMuscleGroup.add("abdutor")
        listMuscleGroup.add("adutor")
        listMuscleGroup.add("biceps")
        listMuscleGroup.add("panturrilha")
        listMuscleGroup.add("peito")
        listMuscleGroup.add("antebraço")
        listMuscleGroup.add("gluteo")
        listMuscleGroup.add("isquiotibiais")
        listMuscleGroup.add("dorsal")
        listMuscleGroup.add("Costas inferior")
        listMuscleGroup.add("Costas meio")
        listMuscleGroup.add("pescoço")
        listMuscleGroup.add("quadriceps")
        listMuscleGroup.add("trapezio")
        listMuscleGroup.add("triceps")

        mAdapter.setData(listMuscleGroup)
    }


}