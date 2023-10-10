package com.example.farmingapp.Activities

import SalesListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.ViewModels.SalesDataViewModel
import com.example.farmingapp.ViewModels.SalesViewModelFactory
import com.example.farmingapp.databinding.ActivityNewSalesBinding
import com.example.farmingapp.db.SalesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewSalesActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewSalesBinding
    private lateinit var newSalesvm: SalesDataViewModel
    private lateinit var salesListAdapter: SalesListAdapter
    private lateinit var salesDataBase: SalesDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        salesDataBase = SalesDataBase.getInstance(this)
        val viewModelFactory = SalesViewModelFactory(salesDataBase)

        newSalesvm = ViewModelProvider(this, viewModelFactory)[SalesDataViewModel::class.java]
        newSalesvm.getSalesData()


        binding.salesBtn.setOnClickListener {
            writeData()


        }

    }

    private fun writeData() {
        val soldIdStr = binding.sId.text.toString().trim()
        val animalId = binding.sAId.text.toString().trim()
        val price = binding.sP.text.toString().trim()
        val quantity = binding.sQ.text.toString().trim()
        val sellingDate = binding.sDate.text.toString().trim()
        val soldBy = binding.sPerson.text.toString().trim()
        val soldTo = binding.pSoldTo.text.toString().trim()

        // Check if soldIdStr is empty
        if (soldIdStr.isEmpty()) {
            Toast.makeText(this, "Sold ID is required", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if soldIdStr is a valid integer
        val soldId = soldIdStr.toIntOrNull()

        if (soldId == null || animalId.isEmpty() || price.isEmpty() ||
            quantity.isEmpty() || sellingDate.isEmpty() || soldBy.isEmpty() || soldTo.isEmpty()
        ) {
            Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            val salesData =
                SalesData(animalId, soldId, price, quantity, sellingDate, soldBy, soldTo)

            GlobalScope.launch(Dispatchers.IO) {
                salesDataBase.salesDao().upsert(salesData)
            }

            // Clear input fields
            binding.sId.text.clear()
            binding.sAId.text.clear()
            binding.sP.text.clear()
            binding.sQ.text.clear()
            binding.sDate.text.clear()
            binding.sPerson.text.clear()
            binding.pSoldTo.text.clear()

            Toast.makeText(this, "Sales successfully Added", Toast.LENGTH_SHORT).show()
        }
    }
}




