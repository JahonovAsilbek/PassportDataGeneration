package com.tuit_21019.passportdatageneration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        itemClick()

        return binding.root
    }

    private fun itemClick() {
        adapter?.onItemClick = object : PassportAdapter.OnItemClick {
            override fun onClick(citizen: Citizen) {
                val bundle = Bundle()
                bundle.putSerializable("citizen", citizen)
                findNavController().navigate(R.id.citizenDataFragment, bundle)
            }
        }
    }

    private fun setToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_backbtn)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadAdapter() {
        adapter?.setAdapter(citizensList!!)
        binding.citizenRv.adapter = adapter
    }

    private fun loadData() {
        citizensList = ArrayList()
        citizensList = citizenDao?.getAllCitizens() as ArrayList
    }

}