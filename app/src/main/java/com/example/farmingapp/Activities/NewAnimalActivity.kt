
package com.example.farmingapp.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmingapp.R
import com.example.farmingapp.databinding.ActivityNewAnimalBinding
import com.example.farmingapp.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewAnimalActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewAnimalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newAnimSaveBtn.setOnClickListener {
            sendDataToAPI()
        }
    }

    private fun sendDataToAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val tagNumber = binding.naTaG.text.toString()
            val animalName = binding.nan.text.toString()
            val animalType = binding.natype.text.toString()
            val animalBreed = binding.nAB.text.toString()
            val animalImage = binding.naimage.text.toString()
            val animalAge = binding.nage.text.toString()
            val animalGender = binding.naGender.text.toString()
            val animalWeight = binding.naw.text.toString()
            val animalColor = binding.nac.text.toString()
            val available = binding.naa.text.toString()

            try {
                RetrofitInstance.api.AddMyAnimals(
                    tagNumber,
                    animalName,
                    animalType,
                    animalBreed,
                    animalImage,
                    animalAge,
                    animalGender,
                    animalWeight,
                    animalColor,
                    available
                )

                withContext(Dispatchers.Main) {
                    // Display success message
                    Toast.makeText(this@NewAnimalActivity, "Animal added", Toast.LENGTH_SHORT).show()

                    // Clear all input fields
                    clearInputFields()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Display error message
                    Toast.makeText(this@NewAnimalActivity, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearInputFields() {
        binding.naTaG.text.clear()
        binding.nan.text.clear()
        binding.natype.text.clear()
        binding.nAB.text.clear()
        binding.naimage.text.clear()
        binding.nage.text.clear()
        binding.naGender.text.clear()
        binding.naw.text.clear()
        binding.nac.text.clear()
        binding.naa.text.clear()
    }
}
