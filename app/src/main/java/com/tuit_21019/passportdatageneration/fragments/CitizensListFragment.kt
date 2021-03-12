package com.tuit_21019.passportdatageneration.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.dao.CitizenDao
import com.tuit_21019.passportdatageneration.database.AppDatabase
import com.tuit_21019.passportdatageneration.entities.Citizen

class CitizensListFragment : Fragment() {

    lateinit var root:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_citizens_list, container, false)

        val db = AppDatabase.getInstance(root.context).citizenDao()
        db.insertCitizen(Citizen(0, "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"))
        Log.d("TATU", "onCreateView: "+        db.getAllCitizens()[0].familyasi)
        return root
    }

}