package com.example.farmingapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.farmingapp.R
import com.example.farmingapp.databinding.ActivityNewAnimalBinding
import com.example.farmingapp.db.PurchaseDataBase

class NewAnimalActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewAnimalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)






    }
}