package com.example.farmingapp.Fragments

import AnimalAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmingapp.Activities.NewAnimalActivity
import com.example.farmingapp.Activities.NewPurchaseActivity
import com.example.farmingapp.Activities.NewSalesActivity
import com.example.farmingapp.Activities.NewWorkerActivity
import com.example.farmingapp.ApiData.AnimalList
import com.example.farmingapp.R
import com.example.farmingapp.ViewModels.HomeViewModel
import com.example.farmingapp.databinding.FragmentHomeBinding
import com.example.farmingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment() : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var animalAdapter: AnimalAdapter
    lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = binding.btmNav
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.NA -> {
                    val intent = Intent(requireContext(), NewAnimalActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.NS -> {
                    val intent = Intent(requireContext(), NewSalesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.NW -> {
                    val intent = Intent(requireContext(), NewWorkerActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.NP -> {
                    val intent = Intent(requireContext(), NewPurchaseActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
    }

}
        binding.sa.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_homeFragment_to_workersFragment)
        }

        binding.AnimL.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_homeFragment_to_animalListFragment)
        }

        binding.WrkL.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_homeFragment_to_registeredWorkersFragment)
        }

        binding.SalesList.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_homeFragment_to_salesListFragment)
        }

        binding.pchList.setOnClickListener {
            val navController =   findNavController()
            navController.navigate(R.id.action_homeFragment_to_purchasedItemsListFragment)
        }


        animalRecyclerview()
        homeViewModel.getAllAnimal()
        observeAnimalsLivedata()
    }

    private fun animalRecyclerview() {
        animalAdapter = AnimalAdapter(emptyList())
        binding.animalsRv.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = animalAdapter
        }
    }

    private fun observeAnimalsLivedata() {
        homeViewModel.observerAnimalLiveData().observe(viewLifecycleOwner
        ) { animals ->
            animalAdapter.setAnimal( animals as ArrayList<com.example.farmingapp.ApiData.Response> )
        }
    }
}

