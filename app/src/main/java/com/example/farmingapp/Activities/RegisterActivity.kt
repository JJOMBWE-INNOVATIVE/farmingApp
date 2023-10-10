package com.example.farmingapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.farmingapp.ApiData.DatabaseHelper
import com.example.farmingapp.R
import com.example.farmingapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

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
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvDontHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
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
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return // Return to exit the method after the user has been successfully registered.
        } else {
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
