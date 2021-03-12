package com.tuit_21019.passportdatageneration.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    var binding:FragmentMenuBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        setCLicks()
        return binding!!.root
    }
    private fun setCLicks() {
        binding!!.citizensListBtn.setOnClickListener {
            findNavController().navigate(R.id.citizensListFragment)
        }
        binding!!.addPassportDataBtn.setOnClickListener {
            findNavController().navigate(R.id.addPasportDataFragment)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}