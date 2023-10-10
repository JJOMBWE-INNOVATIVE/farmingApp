package com.example.farmingapp.Fragments

import WorkersListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmingapp.Activities.MainActivity
import com.example.farmingapp.ViewModels.WorkersViewModel
import com.example.farmingapp.databinding.FragmentRegisteredWorkersBinding


class RegisteredWorkersFragment : Fragment() {
    lateinit var binding:FragmentRegisteredWorkersBinding
    lateinit var workersListViewModel: WorkersViewModel
    lateinit var workersListAdapter: WorkersListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workersListViewModel = (activity as MainActivity).myWorkersListViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisteredWorkersBinding.inflate(layoutInflater)
        return (binding.root)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    observeWorkersdata()
    prepareRecyclerView()

    }

    private fun prepareRecyclerView() {
        workersListAdapter = WorkersListAdapter(emptyList())
        binding.RegisteredWorkersrecyclerView.apply {
            layoutManager = GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false)
              adapter =  workersListAdapter
        }

    }

    private fun observeWorkersdata() {
        workersListViewModel.observeMyWorkersLiveData().observe(viewLifecycleOwner, Observer {
            workers ->
            workersListAdapter.getWorkersData(workers)
            binding.numRegWorkers.text = workers.size.toString()
        })
    }

}