package com.app.ufit.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.ufit.R
import com.app.ufit.databinding.FragmentLoginBinding
import com.app.ufit.databinding.FragmentProfileBinding
import com.app.ufit.viewmodels.login.LoginViewModel
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
        mProfileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        mProfileViewModel.success.observe(requireActivity()) {
           binding.tvProfileName.text = it.name
            binding.tvHeight.text = it.height
            binding.tvWeight.text = it.weight
            binding.tvAge.text = it.birthDate.toString()

        }

        mProfileViewModel.getUserFromSession()

        return binding.root

    }

}