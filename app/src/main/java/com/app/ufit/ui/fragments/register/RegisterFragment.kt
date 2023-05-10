package com.app.ufit.ui.fragments.register

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.ufit.R
import com.app.ufit.databinding.FragmentRegisterBinding
import com.app.ufit.models.User
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener { register() }

        return binding.root
    }


    private fun register() {
        val name = binding.etName.text.toString()
        val lastname = binding.etLastname.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmedPass = binding.etPasswordConfirmation.text.toString()
        val date = Date()

        if (isValidForm(name, lastname, email, password, confirmedPass)) {
            val user = User("", name, lastname, "", date, "", "", email, password)
            val action =
                RegisterFragmentDirections.actionRegisterFragment2ToRegisterInfoFragment(user)
            findNavController().navigate(action)
        }
    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun isValidForm(
        name: String,
        lastname: String,
        email: String,
        password: String,
        confirmedPass: String
    ): Boolean {

        when {
            name.isEmpty() -> {
                binding.tlName.helperText = getString(R.string.obrigatory_field)
                binding.tlName.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }

            lastname.isEmpty() -> {
                binding.tlLastname.helperText = getString(R.string.obrigatory_field)
                binding.tlLastname.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }

            email.isEmpty() && !email.isEmailValid() -> {
                binding.tlEmail.helperText = getString(R.string.obrigatory_field)
                binding.tlEmail.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }

            password.isEmpty() -> {
                binding.tlPassword.helperText = getString(R.string.obrigatory_field)
                binding.tlPassword.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }

            confirmedPass.isEmpty() -> {
                binding.tlPasswordConfirmation.helperText = getString(R.string.obrigatory_field)
                binding.tlPasswordConfirmation.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }

            password != confirmedPass -> {
                binding.tlPasswordConfirmation.helperText = getString(R.string.password_dont_match)
                binding.tlPasswordConfirmation.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }

            else -> {
                return true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}