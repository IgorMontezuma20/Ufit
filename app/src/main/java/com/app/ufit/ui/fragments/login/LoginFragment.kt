package com.app.ufit.ui.fragments.login

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.ufit.R
import com.app.ufit.databinding.FragmentLoginBinding
import com.app.ufit.models.User
import com.app.ufit.viewmodels.login.LoginViewModel
import com.app.ufit.viewmodels.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.internal.Contexts.getApplication


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private lateinit var mLoginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        setLoadingProgressbar()

        mLoginViewModel.success.observe(requireActivity()) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.btnEntrar.setOnClickListener {
            loginCheckFields()

        }

        mLoginViewModel.getUserFromSession()

        binding.constLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }


        return binding.root
    }

    private fun loginCheckFields() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()


        if (isValidForm(email, password)) {

            mLoginViewModel.loginUser(email, password)

        }

    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun isValidForm(email: String, password: String): Boolean {

        when {
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
            else -> {
                return true
            }

        }

    }

    private fun setLoadingProgressbar() {
        mLoginViewModel.load.observe(requireActivity()) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }


}