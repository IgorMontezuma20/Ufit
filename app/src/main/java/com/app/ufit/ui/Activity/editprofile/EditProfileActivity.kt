package com.app.ufit.ui.Activity.editprofile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.load
import com.app.ufit.R
import com.app.ufit.databinding.ActivityEditProfileBinding
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.app.ufit.viewmodels.profile.ProfileViewModel
import com.app.ufit.viewmodels.profileImage.ProfileImageViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var menuItem: MenuItem

    private lateinit var navController: NavController

    private lateinit var mProfileViewModel: ProfileViewModel

    private lateinit var mProfileImageViewModel: ProfileImageViewModel

    private var imageFile: File? = null

    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProfileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        mProfileImageViewModel = ViewModelProvider(this)[ProfileImageViewModel::class.java]

        binding.ivProfileImage.setOnClickListener {
            selectImage()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val lastname = binding.etLastname.text.toString()
            mProfileImageViewModel.updateData(imageFile, name, lastname)
            mProfileImageViewModel.saveImage(imageFile)

            finish()
        }


        mProfileViewModel.success.observe(this) {
            binding.etName.setText(it.name)
            binding.etLastname.setText(it.lastName)
            binding.ivProfileImage.load(it.image)
        }

        mProfileViewModel.getUserFromSession()
    }

    private val startImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == RESULT_OK) {
                val fileUri = data?.data
                imageFile =
                    File(fileUri?.path) // EL ARCHIVO QUE VAMOS A GUARDAR COMO IMAGEN EN EL SERVIDOR
                binding.ivProfileImage.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
            } else {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_profile_menu, menu)
        menuItem = menu!!.findItem(R.id.confirm_update)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}