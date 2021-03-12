package com.tuit_21019.passportdatageneration.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuit_21019.passportdatageneration.databinding.ItemPassportBinding
import com.tuit_21019.passportdatageneration.entities.Citizen

class PassportAdapter : RecyclerView.Adapter<PassportAdapter.VH>() {

    private var citizenList: ArrayList<Citizen>? = null

    fun setAdapter(citizenList: ArrayList<Citizen>) {
        this.citizenList = citizenList
    }

    inner class VH(itemPassportBinding: ItemPassportBinding) :
        RecyclerView.ViewHolder(itemPassportBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemPassportBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun getItemCount(): Int = citizenList!!.size

}