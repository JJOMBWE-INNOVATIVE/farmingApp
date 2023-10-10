package com.example.farmingapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.farmingapp.Fragments.HomeFragment
import com.example.farmingapp.R
import com.example.farmingapp.ViewModels.PurchaseListViewModelFactory
import com.example.farmingapp.ViewModels.SalesListViewModel
import com.example.farmingapp.ViewModels.SalesListViewModelFactory
import com.example.farmingapp.ViewModels.WorkersViewModel
import com.example.farmingapp.ViewModels.WorkersListViewModelFactory
import com.example.farmingapp.ViewModels.purchaseListViewModel
import com.example.farmingapp.databinding.ActivityMainBinding
import com.example.farmingapp.db.PurchaseDataBase
import com.example.farmingapp.db.SalesDataBase
import com.example.farmingapp.db.WorkersDataBase

class MainActivity : AppCompatActivity() {
    val mysalesListViewModel : SalesListViewModel by lazy {
        val salesDataBase = SalesDataBase.getInstance(this)
        val saleListProviderFactory = SalesListViewModelFactory(salesDataBase)
        ViewModelProvider(this,saleListProviderFactory)[SalesListViewModel::class.java]
    }

    val mypurchaseListViewModel : purchaseListViewModel by lazy {
        val purchaseDataBase = PurchaseDataBase.getInstance(this)
        val purchaseListViewModelFactory = PurchaseListViewModelFactory(purchaseDataBase)
        ViewModelProvider(this,purchaseListViewModelFactory)[purchaseListViewModel::class.java]
    }

    val myWorkersListViewModel : WorkersViewModel by lazy {
        val workersDataBase = WorkersDataBase.getInstance(this)
        val workersListProviderFactory = WorkersListViewModelFactory(workersDataBase)
        ViewModelProvider(this,workersListProviderFactory)[WorkersViewModel::class.java]
    }



    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)
//        val homeFragment = HomeFragment(navController)
//
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fragmentContainerView, homeFragment)
//        transaction.commit()


    }
}