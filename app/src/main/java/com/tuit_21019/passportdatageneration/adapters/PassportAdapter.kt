package com.tuit_21019.passportdatageneration.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuit_21019.passportdatageneration.databinding.ItemPassportBinding
import com.tuit_21019.passportdatageneration.entities.Citizen

class PassportAdapter : RecyclerView.Adapter<PassportAdapter.VH>() {

    private var citizenList: ArrayList<Citizen>? = null
    var onItemClick: OnItemClick? = null

    fun setAdapter(citizenList: ArrayList<Citizen>) {
        this.citizenList = citizenList
    }

    inner class VH(var itemPassportBinding: ItemPassportBinding) :
        RecyclerView.ViewHolder(itemPassportBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(citizen: Citizen) {
            itemPassportBinding.sequence.text = citizen.id.toString() + "."
            itemPassportBinding.name.text = "${citizen.familyasi} ${citizen.ismi}"
            itemPassportBinding.passport.text = citizen.passport_raqami

            itemPassportBinding.root.setOnClickListener {
                if (onItemClick != null) {
                    onItemClick!!.onClick(citizen)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemPassportBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(citizenList!![position])
    }

    override fun getItemCount(): Int = citizenList!!.size

    interface OnItemClick {
        fun onClick(citizen: Citizen)
    }
}