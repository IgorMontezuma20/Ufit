package com.app.ufit.ui.Activity.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.load
import com.app.ufit.R
import com.app.ufit.databinding.ActivityEditProfileBinding
import com.app.ufit.viewmodels.profile.ProfileViewModel

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var menuItem: MenuItem

    private lateinit var navController: NavController

    private lateinit var mProfileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProfileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        mProfileViewModel.success.observe(this) {
            binding.etName.setText(it.name)
            binding.etLastname.setText(it.lastName)
            binding.ivProfileImage.load(it.image)
            //binding.acGender.setText(it.acGender)

        }

        mProfileViewModel.getUserFromSession()

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