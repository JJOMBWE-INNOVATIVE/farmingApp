package com.example.farmingapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.farmingapp.Activities.LoginActivity
import com.example.farmingapp.ApiData.DatabaseHelper
import com.example.farmingapp.R
import com.example.farmingapp.databinding.FragmentRegisterBinding
import com.example.farmingapp.databinding.NewSalesBinding


class RegisterFragment : Fragment() {
   lateinit var binding: FragmentRegisterBinding
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RegisterButton.setOnClickListener {
            val rUserFNAME = binding.edFirstNameRegister.text.toString().trim()
            val rUserLNAME = binding.edLastNameRegister.text.toString().trim()
            val rUserAGE = binding.age.text.toString().trim()
            val rUserTITLE = binding.editTextId.text.toString().trim()
            val rUserPHONE = binding.edEmailRegister.text.toString().trim()
            val rUserPASSWORD = binding.edPasswordRegister.text.toString().trim()

            if (validateInput(
                    rUserFNAME,
                    rUserLNAME,
                    rUserAGE,
                    rUserTITLE,
                    rUserPHONE,
                    rUserPASSWORD
                )
            ) {
                register(
                    rUserFNAME,
                    rUserLNAME,
                    rUserAGE,
                    rUserTITLE,
                    rUserPHONE,
                    rUserPASSWORD
                )
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvDontHaveAccount.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun validateInput(
        UserFNAME: String,
        UserLNAME: String,
        UserAGE: String,
        UserTITLE: String,
        UserPHONE: String,
        UserPASSWORD: String
    ): Boolean {
        return UserFNAME.isNotEmpty() &&
                UserLNAME.isNotEmpty() &&
                UserAGE.isNotEmpty() &&
                UserTITLE.isNotEmpty() &&
                UserPHONE.isNotEmpty() &&
                UserPASSWORD.isNotEmpty()
    }

    private fun register(
        UserFNAME: String,
        UserLNAME: String,
        UserAGE: String,
        UserTITLE: String,
        UserPHONE: String,
        UserPASSWORD: String
    ) {
        val insertedRowId =
            databaseHelper.insertUser(
                UserFNAME,
                UserLNAME,
                UserAGE,
                UserTITLE,
                UserPHONE,
                UserPASSWORD
            )
        if (insertedRowId != -1L) {
            Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show()
            val navController =   findNavController()
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        } else {
            Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_SHORT).show()
        }
    }



    }

