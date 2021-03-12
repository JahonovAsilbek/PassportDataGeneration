package com.tuit_21019.passportdatageneration.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tuit_21019.passportdatageneration.databinding.SpinnerItemBinding

class MySpinnerAdapter(var list: ArrayList<String>) : BaseAdapter() {

    var binding: SpinnerItemBinding? = null

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        binding!!.spinnerItemTv.text = list[position]

        return binding!!.root
    }
}