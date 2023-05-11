package com.app.ufit.ui.fragments.home

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ufit.R
import com.app.ufit.adapters.MuscleGroupAdapter
import com.app.ufit.databinding.CustomExitDialogBinding
import com.app.ufit.databinding.FragmentHomeBinding
import com.app.ufit.models.User
import com.app.ufit.viewmodels.MainViewModel
import com.app.ufit.viewmodels.delete.DeleteUserViewModel


class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    private val mAdapter by lazy { MuscleGroupAdapter() }

    private val mAdapterExerciseTypes by lazy { MuscleGroupAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupRecyclerViewExerciseTypes()
        binding.btnMap.setOnClickListener {
            openMapsDialog()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

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

    private fun setupRecyclerViewExerciseTypes() {
        binding.recyclerViewExerciseTypes.adapter = mAdapterExerciseTypes
        binding.recyclerViewExerciseTypes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        val listExercisetypes = arrayListOf<String>()
        listExercisetypes.add("cardio")
        listExercisetypes.add("olympic weightlifting")
        listExercisetypes.add("plyometrics")
        listExercisetypes.add("powerlifting")
        listExercisetypes.add("strength")
        listExercisetypes.add("stretching")
        listExercisetypes.add("strongman")

        mAdapterExerciseTypes.setData(listExercisetypes)
    }


    private fun openMapsLink() {
        val intentUri = "geo:?q=encontre as academias mais proximas"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(intentUri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
    private fun openMapsDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_maps_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(view)
        val dialogBinding = CustomExitDialogBinding.bind(view)
        val confirmButton = dialogBinding.confirmButton
        val cancelButton = dialogBinding.cancelButton

        val mAlertDialog = dialog.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }

        confirmButton.setOnClickListener {
            openMapsLink()

        }
        cancelButton.setOnClickListener {
            mAlertDialog.cancel()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}