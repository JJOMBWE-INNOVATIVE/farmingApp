package com.example.farmingapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.farmingapp.ApiData.PurchaseData
import com.example.farmingapp.R
import com.example.farmingapp.databinding.ActivityNewPurchaseBinding
import com.example.farmingapp.db.PurchaseDataBase
import com.example.farmingapp.db.SalesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.EditText

class NewPurchaseActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewPurchaseBinding
    private lateinit var purchaseDataBase:PurchaseDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        purchaseDataBase = PurchaseDataBase.getInstance(this)

        binding.purchaseSaveBtn.setOnClickListener {
            savePurchases()
        }

    }

    private fun savePurchases() {
        val purchaseId = binding.pId.text.toString()
        val productPrice = binding.pP.text.toString()
        val productName = binding.pN.text.toString()
        val productDescription = binding.pDescr.text.toString()
        val purchaseDate = binding.pDate.text.toString()
        val purchaseQuantity = binding.pQnty.text.toString()
        val purchasedFrom = binding.pFrom.text.toString()

        if (purchaseId.isEmpty() || productPrice.isEmpty() || productName.isEmpty() ||
            productDescription.isEmpty() || purchaseDate.isEmpty() ||
            purchaseQuantity.isEmpty() || purchasedFrom.isEmpty()) {

            Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()

        } else {

            val purchaseData = PurchaseData(
                purchaseId, productPrice, productName, productDescription,
                purchaseDate, purchaseQuantity, purchasedFrom
            )

            GlobalScope.launch(Dispatchers.IO) {
                purchaseDataBase.purchaseDao().insertNewPurchase(purchaseData)
            }

            binding.pId.text.clear()
            binding.pP.text.clear()
            binding.pN.text.clear()
            binding.pDescr.text.clear()
            binding.pDate.text.clear()
            binding.pQnty.text.clear()
            binding.pFrom.text.clear()

            Toast.makeText(this, "Purchase successfully Added", Toast.LENGTH_SHORT).show()
        }








    }
}