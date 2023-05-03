package com.app.ufit.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.app.ufit.R
import com.app.ufit.databinding.FragmentProfileBinding
import com.app.ufit.ui.MainActivity
import com.app.ufit.ui.fragments.login.LoginFragment
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

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        mProfileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileActivity)
        }

        binding.btnLogout.setOnClickListener {
            logOff()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)

        }

        mProfileViewModel.success.observe(requireActivity()) {
            binding.tvProfileName.text = it.name
            //binding.tvProfileLastName.text = it.lastName
            binding.tvHeight.text = "${it.height} cm"
            binding.tvWeight.text = "${it.weight} Kg"
            binding.ivProfileImage.load(it.image)
            binding.tvAge.text = mProfileViewModel.getUserAge()

        }

        mProfileViewModel.getUserFromSession()

        return binding.root

    }

    private fun logOff() {
        mProfileViewModel.logout()
        // Criar uma nova intenção para iniciar a LoginActivity
        val intent = Intent(activity, MainActivity::class.java)

        // Limpar a pilha de atividades e iniciar a nova atividade LoginActivity
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)


    }

    override fun onDestroy() {
        super.onDestroy()

    }

}