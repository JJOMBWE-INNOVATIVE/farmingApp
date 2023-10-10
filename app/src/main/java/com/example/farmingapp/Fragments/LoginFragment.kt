package com.example.farmingapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.farmingapp.ApiData.DatabaseHelper
import com.example.farmingapp.R
import com.example.farmingapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding:FragmentLoginBinding
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lgButton.setOnClickListener {
            val lUserTITLE = binding.lgId.text.toString()
            val lUserPHONE = binding.lgPhone.text.toString()
            val lUserPASSWORD = binding.lgPassword.text.toString()
            login(lUserTITLE, lUserPHONE, lUserPASSWORD)
        }

        binding.lgButton.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun login(UserTITLE: String, UserPHONE: String, UserPASSWORD: String) {
        val userExists = databaseHelper.readUser(UserTITLE, UserPHONE, UserPASSWORD)
        if (userExists) {
            Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            val navController =   findNavController()
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }


    }

