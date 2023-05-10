package com.app.ufit.ui.fragments.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.app.ufit.databinding.FragmentEditProfileBinding
import com.app.ufit.viewmodels.profile.ProfileViewModel

class EditProfileFragment : Fragment() {


    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mProfileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mProfileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        mProfileViewModel.success.observe(requireActivity()) {
            binding.etName.setText(it.name)
            binding.etLastname.setText(it.lastName)
            binding.ivProfileImage.load(it.image)
        }

        mProfileViewModel.getUserFromSession()

        return binding.root
    }


}