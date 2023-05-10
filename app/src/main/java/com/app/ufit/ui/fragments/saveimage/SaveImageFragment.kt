package com.app.ufit.ui.fragments.saveimage

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.ufit.R
import com.app.ufit.databinding.FragmentSaveImageBinding
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.app.ufit.viewmodels.profileImage.ProfileImageViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File


class SaveImageFragment : Fragment() {
    private var _binding: FragmentSaveImageBinding? = null
    private val binding get() = _binding!!

    private var circleImage: CircleImageView? = null
    private var imageFile: File? = null

    var usersProvider = UsersProvider()
    var user: User? = null

    lateinit var mProfileImageViewModel: ProfileImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSaveImageBinding.inflate(inflater, container, false)

        mProfileImageViewModel =
            ViewModelProvider(requireActivity())[ProfileImageViewModel::class.java]

        circleImage = binding.circleImageUser
        binding.circleImageUser.setOnClickListener { selectImage() }

        binding.btnConfirm.setOnClickListener { mProfileImageViewModel.saveImage(imageFile) }
        return binding.root

    }

    private val startImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data
                imageFile = File(fileUri?.path)
                circleImage?.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "A tarefa foi cancelada.", Toast.LENGTH_LONG).show()
            }
        }

    private fun selectImage() {
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent {
                startImageForResult.launch(it)
            }
    }
}