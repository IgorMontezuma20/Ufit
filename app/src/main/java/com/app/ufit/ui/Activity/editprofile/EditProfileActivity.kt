package com.app.ufit.ui.Activity.editprofile

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.app.ufit.viewmodels.profile.ProfileViewModel
import com.app.ufit.viewmodels.profileImage.ProfileImageViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var menuItem: MenuItem

    private lateinit var navController: NavController

    private lateinit var mProfileViewModel: ProfileViewModel

    private lateinit var mProfileImageViewModel: ProfileImageViewModel

    private var imageFile: File? = null

    private val user: User? = null

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
        }


        mProfileViewModel.success.observe(this) {
            binding.etName.setText(it.name)
            binding.etLastname.setText(it.lastName)
            binding.ivProfileImage.load(it.image)
            //binding.acGender.setText(it.acGender)

        }

        mProfileViewModel.getUserFromSession()
    }
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.navHostFragment) as NavHostFragment
//        navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
////                R.id.homeFragment,
////                R.id.favoritesFragment,
////                R.id.profileFragment
//            )
//        )

//        private fun updateData() {
//
//            val name = binding.etName.text.toString()
//            val lastname = binding.etLastname.text.toString()
//
//            user?.name = name
//            user?.lastName = lastname
//
//
//            if (imageFile != null) {
//                usersProvider.update(imageFile!!, user!!)?.enqueue(object: Callback<ResponseHttp> {
//                    override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
//
//                        Log.d(TAG, "RESPONSE: $response")
//                        Log.d(TAG, "BODY: ${response.body()}")
//
//                        Toast.makeText(this@EditProfileActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
//
//                        mProfileImageViewModel.saveUserInSession(response.body()?.data.toString())
//
//                    }
//
//                    override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
//                        Log.d(TAG, "Error: ${t.message}")
//                        Toast.makeText(this@EditProfileActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
//                    }
//
//                })
//            }
//            else {
//                usersProvider.updateWithoutImage(user!!)?.enqueue(object: Callback<ResponseHttp> {
//                    override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
//
//                        Log.d(TAG, "RESPONSE: $response")
//                        Log.d(TAG, "BODY: ${response.body()}")
//
//                        Toast.makeText(this@EditProfileActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
//
//                        mProfileImageViewModel.saveUserInSession(response.body()?.data.toString())
//
//                    }
//
//                    override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
//                        Log.d(TAG, "Error: ${t.message}")
//                        Toast.makeText(this@EditProfileActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
//                    }
//
//                })
//            }
//
//
//
//        }


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
                Toast.makeText(this, "Tarea se cancelo", Toast.LENGTH_LONG).show()
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
        //checkSavedRecipes(menuItem)
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