package com.app.ufit.ui.fragments.register

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.app.ufit.R
import com.app.ufit.databinding.FragmentRegisterInfoBinding
import com.app.ufit.models.User
import com.app.ufit.util.StringHelper
import com.app.ufit.viewmodels.register.RegisterInfoViewModel
import com.vicmikhailau.maskededittext.MaskedFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RegisterInfoFragment : Fragment() {

    private var _binding: FragmentRegisterInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRegisterInfoViewModel: RegisterInfoViewModel

    private var gender = ""
    private var weight = ""
    private var height = ""
    private var datePicker = ""

    private val args: RegisterInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterInfoBinding.inflate(inflater, container, false)

        mRegisterInfoViewModel =
            ViewModelProvider(requireActivity())[RegisterInfoViewModel::class.java]


        val genders = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genders
        )
        binding.acGender.setAdapter(adapter)

        binding.btnRegister.setOnClickListener {
            createAccount()
        }

        binding.acGender.setOnItemClickListener { adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            gender = adapterView.getItemAtPosition(i).toString()
        }

        binding.etBirth.setOnClickListener {
            setDate()
        }


        return binding.root
    }

    private fun setDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val returnDate = "$dayOfMonth ${monthOfYear + 1}  $year"
                val date = StringHelper.parseDate(
                    "dd MM yyyy",
                    "dd/MM/yyyy",
                    returnDate
                )
                val dateString = date
                datePicker = dateString
                binding.etBirth.setText(StringHelper.parseDate("dd/MM/yyyy", "dd MM yyyy", date))
                binding.etBirth.error = null
                Toast.makeText(
                    requireContext(),
                    "pick date input format and display $dateString",
                    Toast.LENGTH_LONG
                ).show()
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = c.timeInMillis
        dpd.show()
    }

    private fun createAccount() {

        if (datePicker.isEmpty()) {
            datePicker = binding.etBirth.text.toString()
            val dateFormatter = MaskedFormatter("##/##/####")
            datePicker = dateFormatter.formatString(datePicker)?.unMaskedString!!


        } else {
            datePicker = StringHelper.parseDate(
                "dd/MM/yyyy",
                "ddMMyyyy", datePicker
            )
        }

        val formatter = SimpleDateFormat("ddMMyyyy")
        val date = formatter.parse(datePicker)

        weight = binding.etWeight.text.toString()
        height = binding.etHeight.text.toString()

        if (isValidForm(gender, datePicker, weight, height)) {
            val user = args.user as User
            user.gender = gender
            user.birthDate = date!!
            user.weight = weight
            user.height = height
            mRegisterInfoViewModel.registerUser(
                user
            )
        }


    }

    private fun isValidForm(
        gender: String,
        birthDate: String,
        weight: String,
        height: String,
    ): Boolean {


        when {
            gender.isEmpty() -> {
                binding.tlGender.helperText = getString(R.string.obrigatory_field)
                binding.tlGender.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
            birthDate.isEmpty() -> {
                binding.tlBirth.helperText = getString(R.string.obrigatory_field)
                binding.tlBirth.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
            weight.isEmpty() -> {
                binding.tlWeight.helperText = getString(R.string.obrigatory_field)
                binding.tlWeight.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }
            height.isEmpty() -> {
                binding.tlHeight.helperText = getString(R.string.obrigatory_field)
                binding.tlHeight.boxStrokeColor = Color.parseColor("#FF0000")
                return false
            }


            else -> {
                return true
            }

        }

    }

}