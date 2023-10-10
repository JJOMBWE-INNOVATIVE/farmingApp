package com.example.farmingapp.Fragments

import WorkersAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmingapp.ApiData.ResponseX
import com.example.farmingapp.ApiData.WorkersData
import com.example.farmingapp.ViewModels.WorkersViewModel
import com.example.farmingapp.ViewModels.WorkersViewModelFactory
import com.example.farmingapp.databinding.FragmentWorkersBinding
import com.example.farmingapp.db.WorkersDataBase


class WorkersFragment : Fragment() {
    lateinit var binding: FragmentWorkersBinding
    lateinit var workersAdapter: WorkersAdapter
    lateinit var workersViewModel: WorkersViewModel
    lateinit var workersDataBase: WorkersDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workersDataBase = WorkersDataBase.getInstance(requireContext())
        workersViewModel = ViewModelProvider(this,WorkersViewModelFactory(workersDataBase))[WorkersViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentWorkersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workersRv()
        workersViewModel.getAllWorkers()
        observerWorkersData()


    }

    private fun workersRv() {
        workersAdapter = WorkersAdapter(emptyList())
        binding.workersRv.apply {
            layoutManager = GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false)
        adapter = workersAdapter
        }
    }

    private fun observerWorkersData() {
        workersViewModel.observeWorkersLiveData().observe(viewLifecycleOwner
        ) { workers ->

            workersAdapter.setWorkers(workers as ArrayList<ResponseX>)
            binding.num.text = workers.size.toString()

        }
    }


}
