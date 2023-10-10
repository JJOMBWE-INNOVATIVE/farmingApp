package com.example.farmingapp.Fragments

import PurchaseListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmingapp.Activities.MainActivity
import com.example.farmingapp.R
import com.example.farmingapp.ViewModels.purchaseListViewModel
import com.example.farmingapp.databinding.FragmentPurchasedItemsListBinding

class PurchasedItemsListFragment : Fragment() {
    lateinit var binding:FragmentPurchasedItemsListBinding
    lateinit var purchaseListViewModel: purchaseListViewModel
    lateinit var purchaseListAdapter : PurchaseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        purchaseListViewModel = (activity as MainActivity).mypurchaseListViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPurchasedItemsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePurchaseRecyclerView()
        observePurchaseDate()
    }
    private fun preparePurchaseRecyclerView() {
        purchaseListAdapter = PurchaseListAdapter(emptyList())
        binding.purchasedSavedRv. apply {
            layoutManager = GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false)
        adapter = purchaseListAdapter
        }
    }

    private fun observePurchaseDate() {
        purchaseListViewModel.observeMySalesLiveData().observe(viewLifecycleOwner, Observer {
            purchase ->
            purchaseListAdapter.setPurchaseData(purchase)
            binding.salesCount.text  = purchase.size.toString()
        })
    }


}