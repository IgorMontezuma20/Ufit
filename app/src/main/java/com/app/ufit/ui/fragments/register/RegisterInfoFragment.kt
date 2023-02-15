package com.app.ufit.ui.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.ufit.R
import com.app.ufit.databinding.FragmentLoginBinding
import com.app.ufit.databinding.FragmentRegisterBinding
import com.app.ufit.databinding.FragmentRegisterInfoBinding


class RegisterInfoFragment : Fragment() {

    private var _binding: FragmentRegisterInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

}