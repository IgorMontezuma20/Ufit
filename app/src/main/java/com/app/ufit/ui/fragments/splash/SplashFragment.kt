package com.app.ufit.ui.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.ufit.R
import com.app.ufit.databinding.FragmentHomeBinding
import com.app.ufit.databinding.FragmentSplashBinding
import com.app.ufit.ui.MainActivity
import com.app.ufit.viewmodels.login.LoginViewModel


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        binding.ltAnimation

//        val bottomNavView = binding?
//        if (activity is MainActivity) {
//            bottomNavView?.visibility = View.VISIBLE
//        } else {
//            bottomNavView?.visibility = View.GONE
//        }

        Handler(Looper.getMainLooper()).postDelayed({

            if (mLoginViewModel.verifyLoggedIn()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }

        }, 3000)



        return binding.root
    }


}