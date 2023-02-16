package com.app.ufit.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.ufit.R
import com.app.ufit.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        binding.btnEntrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_exercisesFragment)
        }

        binding.constLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }


        return binding.root
    }


}