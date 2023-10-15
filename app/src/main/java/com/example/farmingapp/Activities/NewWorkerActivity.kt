package com.example.farmingapp.Activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.farmingapp.ApiData.WorkersData
import com.example.farmingapp.databinding.ActivityNewWorkerBinding
import com.example.farmingapp.db.WorkersDataBase
import com.example.farmingapp.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewWorkerActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewWorkerBinding
    private lateinit var workersDataBase: WorkersDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workersDataBase = WorkersDataBase.getInstance(this)

        binding.wksSaveBtn.setOnClickListener {

            saveWorkers()
            sendDataToAPI()

        }


    }

    private fun sendDataToAPI() {
        CoroutineScope(Dispatchers.IO).launch{
            val firstName = binding.wkFN.text.toString()
            val secondName = binding.wkLN.text.toString()
            val gender = binding.gender.text.toString()
            val title = binding.wkTitle.text.toString()
            var phone = binding.wkPhone.text.toString()
            val password = binding.wkPassword.text.toString()

            try {
                RetrofitInstance.api.AddMyWorker(
                    firstName,
                    secondName,
                    title,
                    phone,
                    password,
                    gender,
                )

                withContext(Dispatchers.Main) {
                    // Display success message
                    Toast.makeText(this@NewWorkerActivity, "Worker added", Toast.LENGTH_SHORT).show()

                    // Clear all input fields
                    clearInputFields()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Display error message
                    Toast.makeText(this@NewWorkerActivity, "Worker not added: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearInputFields() {
        binding.wkId.text.clear()
        binding.wkFN.text.clear()
        binding.wkLN.text.clear()
        binding.gender.text.clear()
        binding.wkTitle.text.clear()
        binding.wkPhone.text.clear()
        binding.wkPassword.text.clear()

    }

    private fun saveWorkers() {
        val wksID = binding.wkId.text.toString()
        var wksAge = 0
        val wkFirstName = binding.wkFN.text.toString()
        val wkLastName = binding.wkLN.text.toString()
        val wksTitle = binding.wkTitle.text.toString()
        val Wksgender = binding.gender.text.toString()
        var wksPhoneNumber = 0
        val wksPassword = binding.wkPassword.text.toString()

        // Boolean variable to track if any field is empty
        var anyFieldEmpty = false

        if (   wksID.isEmpty() ||  Wksgender.isEmpty() ||
            wkFirstName.isEmpty() || wkLastName.isEmpty() || wksPassword.isEmpty()) {
            anyFieldEmpty = true
        }

        try {
            if (binding.wkAge.text.isNotEmpty()) {
                wksAge = binding.wkAge.text.toString().toInt()
            } else {
                anyFieldEmpty = true
            }

            if (binding.wkPhone.text.isNotEmpty()) {
                wksPhoneNumber = binding.wkPhone.text.toString().toInt()
            } else {
                anyFieldEmpty = true
            }

            if (anyFieldEmpty) {
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
                return // Exit the function early if any field is empty
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
            return // Exit the function early if the format is invalid
        }

        val workersData = WorkersData(
            wksID,
            wksAge,
            wkFirstName,
            wkLastName,
            wksPassword,
            wksPhoneNumber,
            wksTitle
        )

        GlobalScope.launch(Dispatchers.IO) {
            workersDataBase.workersDao().insertNewWorkers(workersData)
        }

        binding.wkId.text.clear()
        binding.wkFN.text.clear()
        binding.wkLN.text.clear()
        binding.wkAge.text.clear()
        binding.wkPhone.text.clear()
        binding.wkPassword.text.clear()
        binding.wkTitle.text.clear()
        binding.gender.text.clear()

        Toast.makeText(this, "Worker successfully Added", Toast.LENGTH_SHORT).show()
    }
}
