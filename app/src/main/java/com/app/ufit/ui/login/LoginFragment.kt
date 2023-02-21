package com.app.ufit.ui.login

import android.content.Context
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

        mLoginViewModel.success.observe(requireActivity()) {
            findNavController().navigate(R.id.action_loginFragment_to_exercisesFragment)
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

    private fun loginCheckFields() : Boolean {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtSenha.text.toString()

        var success = false

        if(isValidForm(email,password)){

            mLoginViewModel.loginUser(email, password)


        }
        else {
            Toast.makeText(this@LoginFragment.context, "O Formulario não é Válido ", Toast.LENGTH_LONG).show()
        }

        return false
//        Log.d("Main", "A senha é: $password")
    }


    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }



    private fun isValidForm(email: String, password: String): Boolean {

        if (email.isBlank()) {
            return false
        }

        if (password.isBlank()) {
            return false
        }

        if (!email.isEmailValid()) {
            return false
        }

        return true
    }


}