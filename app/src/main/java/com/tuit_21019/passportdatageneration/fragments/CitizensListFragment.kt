package com.tuit_21019.passportdatageneration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.adapters.PassportAdapter
import com.tuit_21019.passportdatageneration.dao.CitizenDao
import com.tuit_21019.passportdatageneration.database.AppDatabase
import com.tuit_21019.passportdatageneration.databinding.FragmentCitizensListBinding
import com.tuit_21019.passportdatageneration.entities.Citizen

class CitizensListFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.get.getDatabase()
        citizenDao = database!!.citizenDao()
        adapter = PassportAdapter()
        setHasOptionsMenu(true)
        setMenuVisibility(true)
    }

    lateinit var binding: FragmentCitizensListBinding
    private var adapter: PassportAdapter? = null
    private var database: AppDatabase? = null
    private var citizenDao: CitizenDao? = null
    private var citizensList: ArrayList<Citizen>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitizensListBinding.inflate(layoutInflater, container, false)
        setToolbar()
        loadData()
        loadAdapter()


        return binding.root
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.ic_backbtn)
    }

    private fun loadAdapter() {
        adapter?.setAdapter(citizensList!!)

    }

    private fun loadData() {
        citizensList = ArrayList()
        citizensList = citizenDao?.getAllCitizens() as ArrayList
    }

}