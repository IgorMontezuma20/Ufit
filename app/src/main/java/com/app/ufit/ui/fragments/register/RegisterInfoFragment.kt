package com.app.ufit.ui.fragments.register

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.ufit.R
import com.app.ufit.databinding.FragmentRegisterInfoBinding
import com.app.ufit.models.User
import com.app.ufit.viewmodels.register.RegisterInfoViewModel
import com.app.ufit.viewmodels.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterInfoFragment : Fragment() {

    private var _binding: FragmentRegisterInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRegisterInfoViewModel: RegisterInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterInfoBinding.inflate(inflater, container, false)

        mRegisterInfoViewModel = ViewModelProvider(requireActivity())[RegisterInfoViewModel::class.java]

        startComponents()

        val gender = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            gender
        )
        binding.acGender.setAdapter(adapter)

        binding.btnRegister.setOnClickListener {
            createAccount()
        }

        return binding.root
    }


//    mRegisterViewModel.registerUser(
//    user = User(
//    name = name,
//    lastName = lastname,
//    gender = gender,
//    email = email,
//    password = password
//    )
//    )

    private fun startComponents() {
        val genderChoice = binding.acGender
        val birthDay = binding.etBirth
        val weight = binding.etWeight
        val height = binding.etHeight
        //val registerButton = binding.button

    }

    private fun createAccount() {
        val genderChoice = binding.acGender.toString()
        val birthDay = binding.etBirth.toString()
        val weight = binding.etWeight.toString()
        val height = binding.etHeight.toString()

        isValidForm(genderChoice)

        mRegisterInfoViewModel.registerUser(
            user = User(
                name = "",
                lastName = "",
                gender = genderChoice,
                email = "",
                password = "",
//                birthDay = birthDay,
//                weight = weight,
//                email = email,
//                password = password
            )
        )

    }

    private fun isValidForm(
        gender: String,
//        birthDay: String,
//        weight: String,
//        height: String,
    ): Boolean {


        when {
            gender.isEmpty() -> {
                binding.tlGender.helperText = getString(R.string.obrigatory_field)
                binding.tlGender.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
//            birthDay.isEmpty() -> {
//                binding.tlBirth.helperText = getString(R.string.obrigatory_field)
//                binding.tlBirth.boxStrokeColor = Color.parseColor("#FF0000")
//                return false
//            }
//            weight.isEmpty() -> {
//                binding.tlWeight.helperText = getString(R.string.obrigatory_field)
//                binding.tlWeight.boxStrokeColor = Color.parseColor("#FF0000")
//                return false
//            }
//            height.isEmpty() -> {
//                binding.tlHeight.helperText = getString(R.string.obrigatory_field)
//                binding.tlHeight.boxStrokeColor = Color.parseColor("#FF0000")
//                return false
//            }


            else -> {
                return true
            }

        }

    }

}