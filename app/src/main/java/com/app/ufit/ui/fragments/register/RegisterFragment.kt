package com.app.ufit.ui.fragments.register

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.app.ufit.R
import com.app.ufit.databinding.FragmentRegisterBinding
import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    val TAG = "FragmentRegister"


    private val usersProvider = UsersProvider()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment2_to_registerInfoFragment)
        }

        return binding.root
    }



    private fun registerUser() {

        val name = binding.etName.text.toString()
        val lastname = binding.etLastname.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmedPass = binding.etPasswordConfirmation.text.toString()

        if (isValidForm(name, lastname, email, password, confirmedPass)) {

            val user = User(

                name = name,
                lastName = lastname,
                email = email,
                password = password,


            )

            usersProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Toast.makeText(
                        this@RegisterFragment.context,
                        response.body()?.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d(TAG, "Response: ${response}")
                    Log.d(TAG, "Body: ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Ocorreu um error ${t.message}")
                    Toast.makeText(
                        this@RegisterFragment.context,
                        "Ocorreu um error ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }


            })

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

        if (name.isBlank()) {
            Toast.makeText(context, "Necessita Colocar um Nome ", Toast.LENGTH_SHORT).show()
            return false
        }
        if (lastname.isBlank()) {
            Toast.makeText(context, "Necessita Colocar um Sobrenome ", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isBlank()) {
            Toast.makeText(context, "Necessita Colocar um Email  ", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isBlank()) {
            Toast.makeText(context, "Necessita Colocar uma Senha ", Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmedPass.isBlank()) {
            Toast.makeText(context, "Necessita Colocar uma confirmação de Senha ", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        if (!email.isEmailValid()) {
            return false

        }
        if (password != confirmedPass) {
            Toast.makeText(context, "As Senhas não coincidem ", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}