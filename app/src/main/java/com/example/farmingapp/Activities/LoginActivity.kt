package com.example.farmingapp.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.farmingapp.ApiData.DatabaseHelper
import com.example.farmingapp.Fragments.HomeFragment
import com.example.farmingapp.R
import com.example.farmingapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var databaseHelper: DatabaseHelper
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)
        binding.login.visibility = View.VISIBLE

        binding.tvDoYouHaveAccount.setOnClickListener {
          val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.lgButton.setOnClickListener {
            val lUserTITLE = binding.lgId.text.toString()
            val lUserPHONE = binding.lgPhone.text.toString()
            val lUserPASSWORD = binding.lgPassword.text.toString()
            login(lUserTITLE, lUserPHONE, lUserPASSWORD)
        }

        binding.lgButton.setOnClickListener {
            val fragmentContainerId: Int = R.id.fragmentContainerView
            val homeFragment = HomeFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(fragmentContainerId, homeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun login(UserTITLE: String, UserPHONE: String, UserPASSWORD: String) {
        val userExists = databaseHelper.readUser(UserTITLE, UserPHONE, UserPASSWORD)
        if (userExists) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            val fragmentContainerId: Int = R.id.fragmentContainerView
            val homeFragment = HomeFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(fragmentContainerId, homeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
