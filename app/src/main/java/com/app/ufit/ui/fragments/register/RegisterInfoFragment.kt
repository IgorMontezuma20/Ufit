package com.app.ufit.ui.fragments.register

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.app.ufit.R
import com.app.ufit.databinding.FragmentRegisterInfoBinding
import com.app.ufit.models.User
import com.app.ufit.viewmodels.register.RegisterInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate

@AndroidEntryPoint
class RegisterInfoFragment : Fragment() {

    private var _binding: FragmentRegisterInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRegisterInfoViewModel: RegisterInfoViewModel

    private var gender = ""
    private var weight = ""
    private var height = ""

    private val args: RegisterInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterInfoBinding.inflate(inflater, container, false)

        mRegisterInfoViewModel =
            ViewModelProvider(requireActivity())[RegisterInfoViewModel::class.java]

//        startComponents()

        val genders = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genders
        )
        binding.acGender.setAdapter(adapter)

        binding.btnRegister.setOnClickListener {
            createAccount()
        }

        binding.acGender.setOnItemClickListener { adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            gender = adapterView.getItemAtPosition(i).toString()
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

//    private fun startComponents() {
//        val genderChoice = binding.acGender
//        val birthDay = binding.etBirth
//        val weight = binding.etWeight
//        val height = binding.etHeight
//        //val registerButton = binding.button
//
//    }

    //    @RequiresApi(Build.VERSION_CODES.O)
    private fun createAccount() {

        //val format = SimpleDateFormat("yyyy/MM/dd")

        val birthDate = binding.etBirth.text.toString()
        //val localDate = LocalDate.parse(birthDate)
        Toast.makeText(requireContext(), birthDate, Toast.LENGTH_SHORT).show()

        val formatter = SimpleDateFormat("ddMMyyyy")

        val date = formatter.parse(birthDate)

        weight = binding.etWeight.text.toString()
        height = binding.etHeight.text.toString()

        if (isValidForm(gender, birthDate, weight, height)) {
            val user = args.user as User
            user.gender = gender
            user.birthDate = date!!
            user.weight = weight
            user.height = height
            mRegisterInfoViewModel.registerUser(
                user
            )
        }


    }

    private fun isValidForm(
        gender: String,
        birthDate: String,
        weight: String,
        height: String,
    ): Boolean {


        when {
            gender.isEmpty() -> {
                binding.tlGender.helperText = getString(R.string.obrigatory_field)
                binding.tlGender.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
            birthDate.isEmpty() -> {
                binding.tlBirth.helperText = getString(R.string.obrigatory_field)
                binding.tlBirth.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
            weight.isEmpty() -> {
                binding.tlWeight.helperText = getString(R.string.obrigatory_field)
                binding.tlWeight.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
            height.isEmpty() -> {
                binding.tlHeight.helperText = getString(R.string.obrigatory_field)
                binding.tlHeight.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }


            else -> {
                return true
            }

        }

    }

}