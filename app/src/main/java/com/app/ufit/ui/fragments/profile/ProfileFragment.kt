package com.app.ufit.ui.fragments.profile

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.app.ufit.R
import com.app.ufit.databinding.CustomExitDialogBinding
import com.app.ufit.databinding.FragmentProfileBinding
import com.app.ufit.models.User
import com.app.ufit.ui.MainActivity
import com.app.ufit.viewmodels.delete.DeleteUserViewModel
import com.app.ufit.viewmodels.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mProfileViewModel: ProfileViewModel
    private lateinit var mDeleteUserViewModel: DeleteUserViewModel

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        mProfileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        mDeleteUserViewModel = ViewModelProvider(requireActivity())[DeleteUserViewModel::class.java]


        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileActivity)
        }

        binding.btnLogout.setOnClickListener {
            openExitDialog()
        }

        binding.btnPersonalData.setOnClickListener {
            openWarningDialog()
        }
        binding.btnAchievements.setOnClickListener {
            openWarningDialog()
        }
        binding.btnActHistory.setOnClickListener {
            openWarningDialog()
        }
        binding.btnProgress.setOnClickListener {
            openWarningDialog()
        }
        binding.btnDeleteAccount.setOnClickListener {
            openDeleteAccountDialog()
        }

        mProfileViewModel.success.observe(requireActivity()) {
            user = it
            binding.tvProfileName.text = it.name
            //binding.tvProfileLastName.text = it.lastName
            binding.tvHeight.text = "${it.height} cm"
            binding.tvWeight.text = "${it.weight} Kg"
            binding.ivProfileImage.load(it.image)
            binding.tvAge.text = mProfileViewModel.getUserAge()

            Toast.makeText(requireContext(), user!!.id.toString(), Toast.LENGTH_SHORT).show()
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

    private fun openExitDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_exit_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(view)
        val dialogBinding = CustomExitDialogBinding.bind(view)
        val confirmButton = dialogBinding.confirmButton
        val cancelButton = dialogBinding.cancelButton

        val mAlertDialog = dialog.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }

        confirmButton.setOnClickListener {
            logOff()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        cancelButton.setOnClickListener {
            mAlertDialog.cancel()
        }

    }

    private fun openWarningDialog() {
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.custom_warning_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(view)
        val dialogBinding = CustomExitDialogBinding.bind(view)
        val confirmButton = dialogBinding.confirmButton
        val cancelButton = dialogBinding.cancelButton

        val mAlertDialog = dialog.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }

        confirmButton.setOnClickListener {
            mAlertDialog.cancel()
        }
    }

    private fun openDeleteAccountDialog() {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.custom_delete_account_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(view)
        val dialogBinding = CustomExitDialogBinding.bind(view)
        val confirmButton = dialogBinding.confirmButton
        val cancelButton = dialogBinding.cancelButton

        val mAlertDialog = dialog.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }

        confirmButton.setOnClickListener {
            mProfileViewModel.logout()
            mDeleteUserViewModel.deleteUser(user?.id.toString())
            logOff()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            mAlertDialog.cancel()
        }

        cancelButton.setOnClickListener {
            mAlertDialog.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}