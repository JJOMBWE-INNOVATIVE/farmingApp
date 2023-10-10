package com.example.farmingapp.Activities

import AnimalDetailsAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmingapp.ViewModels.AnimalDetailsViewModel
import com.example.farmingapp.databinding.ActivityAnimalDetailsBinding
import com.example.farmingapp.databinding.DpAnimalDetailsBinding

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailsBinding
    private val animalDetailsViewModel: AnimalDetailsViewModel by viewModels()
    private lateinit var animalDetailsAdapter: AnimalDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareDetailsRecyclerView()
        animalDetailsViewModel.getAllAnimalImages()
        observeAnimalDetailsLiveData()
    }

    private fun prepareDetailsRecyclerView() {
        animalDetailsAdapter = AnimalDetailsAdapter()
        binding.animalDetailsRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = animalDetailsAdapter
        }
    }

    private fun observeAnimalDetailsLiveData() {
        animalDetailsViewModel.observerAnimalDetailsLiveData().observe(this, Observer { animals ->
            binding.animalCount.text = animals.size.toString()
            animalDetailsAdapter.setAnimalsDetails(animals)
        })
    }
}
