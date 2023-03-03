package com.app.ufit.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.ufit.R
import com.app.ufit.databinding.FragmentHomeBinding
import com.app.ufit.databinding.FragmentProfileBinding
import com.app.ufit.viewmodels.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mProfileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        _binding  = FragmentProfileBinding.inflate(inflater, container, false)
        mProfileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        mProfileViewModel.success.observe(requireActivity()) {
            binding.tvProfileName.text = it.name
            binding.tvHeight.text = "${it.height} cm"
            binding.tvWeight.text = "${it.weight} Kg"
            binding.tvAge.text = mProfileViewModel.getUserAge()

        }

        mProfileViewModel.getUserFromSession()

        return binding.root

    }

}