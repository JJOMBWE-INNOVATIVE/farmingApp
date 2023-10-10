package com.example.farmingapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.farmingapp.Activities.AnimalDetailsActivity
import com.example.farmingapp.Activities.MainActivity
import com.example.farmingapp.ApiData.AddAnimal
import com.example.farmingapp.ViewModels.AnimalDetailsViewModel
import com.example.farmingapp.databinding.FragmentSearchAnimalsBinding
import com.example.farmingapp.db.AddAnimalDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsSearchFragment : Fragment() {
    lateinit var binding: FragmentSearchAnimalsBinding
    lateinit var addAnimalDataBase: AddAnimalDataBase
    lateinit var addAnimalViewModel : AnimalDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addAnimalDataBase = AddAnimalDataBase.getInstance(requireContext())
        //addAnimalViewModel = ( activity  as MainActivity).myAddAnimalViewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchAnimalsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.SB.setOnClickListener {
//            val intent = Intent(requireContext(), AnimalDetailsActivity::class.java)
//            startActivity(intent)
            saveAnimal()
        }


    }

    private fun saveAnimal() {
        val aIDText = binding.AID.text.toString()
        val aID = if (aIDText.isNotEmpty() && aIDText.toIntOrNull() != null) {
            aIDText.toInt()
        } else {
            0
        }

        val aAge = binding.AA.text.toString()
        val aAvailable = binding.AV.text.toString()
        val aBreed = binding.AB.text.toString()
        val aGender = binding.AG.text.toString()
        val aColor = binding.AC.text.toString()
        val aName = binding.AN.text.toString()
        val aTag = binding.TN.text.toString()
        val aType = binding.T.text.toString()
        val aWeight = binding.AW.text.toString()

        if (aID == 0 || aAge.isEmpty() || aAvailable.isEmpty() || aBreed.isEmpty() ||
            aGender.isEmpty() || aColor.isEmpty() || aName.isEmpty() || aTag.isEmpty() ||
            aType.isEmpty() || aWeight.isEmpty()
        ) {
            Toast.makeText(requireActivity(), "Fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            // Create an instance of AddAnimal and populate it with the data
            val addAnimal = AddAnimal(
                aID,
                aAge,
                aAvailable,
                aBreed,
                aGender,
                aColor,
                aName,
                aTag,
                aType,
                aWeight
            )

            // Insert the populated AddAnimal object into the database
            GlobalScope.launch(Dispatchers.IO) {
                addAnimalDataBase.addAnimalDao().insertAddAnimal(addAnimal)
            }

            binding.AID.setText("")
            binding.AA.setText("")
            binding.AV.setText("")
            binding.AB.setText("")
            binding.AG.setText("")
            binding.AC.setText("")
            binding.AN.setText("")
            binding.TN.setText("")
            binding.T.setText("")
            binding.AW.setText("")

            Toast.makeText(requireActivity(), "Animal successfully saved", Toast.LENGTH_SHORT).show()
        }
    }
}
