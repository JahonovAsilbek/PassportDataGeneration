package com.tuit_21019.passportdatageneration.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.databinding.FragmentAddPasportDataBinding


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AddPasportDataFragment : Fragment() {
    lateinit var binding:FragmentAddPasportDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        root = inflater.inflate(R.layout.fragment_add_pasport_data, container, false)
        binding = FragmentAddPasportDataBinding.inflate(inflater, container, false)
        setToolbar()
        return binding.root
    }


    private fun setToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}