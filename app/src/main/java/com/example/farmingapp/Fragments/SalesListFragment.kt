package com.example.farmingapp.Fragments

import SalesAdapter
import SalesListAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmingapp.Activities.MainActivity
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.ViewModels.SalesListViewModel
import com.example.farmingapp.databinding.FragmentSalesListBinding
import com.example.farmingapp.db.SalesDataBase


class SalesListFragment : Fragment() {

    private lateinit var  salesListViewModel : SalesListViewModel
    private lateinit var binding: FragmentSalesListBinding
    private lateinit var salesAdapter: SalesAdapter
    lateinit var salesDataBase: SalesDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        salesDataBase = SalesDataBase.getInstance(requireContext())
    salesListViewModel = (activity as MainActivity).mysalesListViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesListBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        prepareRecyclerView()
        observeSalesListData()



    }

    private fun prepareRecyclerView() {
        salesAdapter = SalesAdapter(emptyList())
        binding.saveSalesRv.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        adapter = salesAdapter
        }
    }

    private fun observeSalesListData() {
        salesListViewModel.observeMySalesLiveData().observe(viewLifecycleOwner, Observer {
            sales ->
            salesAdapter.setSales(sales as ArrayList<SalesData>)
            binding.salesC.text = sales.size.toString()
            }
        )}
    }

