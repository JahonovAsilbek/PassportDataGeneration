package com.tuit_21019.passportdatageneration.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.tuit_21019.passportdatageneration.adapters.MySpinnerAdapter
import com.tuit_21019.passportdatageneration.databinding.FragmentAddPasportDataBinding


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AddPasportDataFragment : Fragment() {
    lateinit var binding:FragmentAddPasportDataBinding
    var viloyatList:ArrayList<String>?=null
    var jinsList: ArrayList<String>? = null

    var viloyatAdapter: MySpinnerAdapter? = null
    var jinsAdapter: MySpinnerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPasportDataBinding.inflate(inflater, container, false)
        setToolbar()
        loadData()
        loadAdapter()


        return binding.root
    }


    private fun setToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun loadData() {
        viloyatList = ArrayList()
        viloyatList!!.add("Andijon")
        viloyatList!!.add("Namangan")
        viloyatList!!.add("Farg'ona")
        viloyatList!!.add("Toshkent")
        viloyatList!!.add("Sirdaryo")
        viloyatList!!.add("Jizzax")
        viloyatList!!.add("Qashqadaryo")
        viloyatList!!.add("Samarqand")
        viloyatList!!.add("Surxondaryo")
        viloyatList!!.add("Navoiy")
        viloyatList!!.add("Buxoro")
        viloyatList!!.add("Xorazm")
        viloyatList!!.add("Qoraqalpog'iston")


        jinsList = ArrayList()
        jinsList!!.add("Erkak")
        jinsList!!.add("Ayol")
    }
    private fun loadAdapter() {
        viloyatAdapter = MySpinnerAdapter(viloyatList!!)
        jinsAdapter = MySpinnerAdapter(jinsList!!)
        binding.viloyatiSpinner.adapter = viloyatAdapter
        binding.jinsiSpinner.adapter=jinsAdapter

    }
}