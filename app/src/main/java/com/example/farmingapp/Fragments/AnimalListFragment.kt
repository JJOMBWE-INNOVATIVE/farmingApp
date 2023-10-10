package com.example.farmingapp.Fragments

import AnimalDetailsAdapter
import AnimalListAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmingapp.Activities.AnimalDetailsActivity
import com.example.farmingapp.ApiData.Response
import com.example.farmingapp.R
import com.example.farmingapp.ViewModels.AnimalListViewModel
import com.example.farmingapp.databinding.FragmentAnimalListBinding


class AnimalListFragment : Fragment() {

    lateinit var binding:FragmentAnimalListBinding
    lateinit var animalListViewModel: AnimalListViewModel
    lateinit var animalListAdapter: AnimalListAdapter
    lateinit var animalDetailsAdapter: AnimalDetailsAdapter
    lateinit var animDetails : Response


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animalListViewModel = ViewModelProvider(this)[AnimalListViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        animalListViewModel.getAllAnimalImages()
        observeAnimasData()
        onClickAnimalsImage()

    }

    private fun onClickAnimalsImage() {
        animalListAdapter.onItemClick = { animal ->
            val intent = Intent(activity,AnimalDetailsActivity::class.java)
            startActivity(intent)
        }
    }


    private fun prepareRecyclerView() {
        animalListAdapter = AnimalListAdapter(emptyList())
        binding.animalListRecyclerview.apply {
        layoutManager = GridLayoutManager(context,4,GridLayoutManager.VERTICAL,false)
       adapter = animalListAdapter
        }
    }

    private fun observeAnimasData() {
        animalListViewModel.observerAnimalListLiveData().observe(viewLifecycleOwner, Observer {
            animals ->
            animalListAdapter.setAnimalList(animals)
            binding.animalList.text = animals.size.toString()

        })
    }

}