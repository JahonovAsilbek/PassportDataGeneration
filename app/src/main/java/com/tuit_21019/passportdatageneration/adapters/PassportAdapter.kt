package com.tuit_21019.passportdatageneration.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.tuit_21019.passportdatageneration.databinding.ItemPassportBinding
import com.tuit_21019.passportdatageneration.entities.Citizen

class PassportAdapter : RecyclerView.Adapter<PassportAdapter.VH>() {

    private var citizenList: ArrayList<Citizen>? = null
    var onItemClick: OnItemClick? = null

    fun setAdapter(citizenList: ArrayList<Citizen>) {
        this.citizenList = citizenList
    }

    fun filterList(filteredList: ArrayList<Citizen>) {
        citizenList = filteredList
        notifyDataSetChanged()
    }

    inner class VH(var itemPassportBinding: ItemPassportBinding) :
        RecyclerView.ViewHolder(itemPassportBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(citizen: Citizen, position: Int) {
            itemPassportBinding.sequence.text = position.toString()
            itemPassportBinding.name.text = "${citizen.surname} ${citizen.name}"
            itemPassportBinding.passport.text = citizen.passportNumber

            itemPassportBinding.root.setOnClickListener {
                if (onItemClick != null) {
                    onItemClick!!.onClick(citizen)
                }
            }

            itemPassportBinding.popup.setOnClickListener {
                if (onItemClick != null) {
                    onItemClick!!.onPopupClick(citizen, itemPassportBinding.popup)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemPassportBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(citizenList!![position], position + 1)
    }

    override fun getItemCount(): Int = citizenList!!.size

    interface OnItemClick {
        fun onClick(citizen: Citizen)
        fun onPopupClick(citizen: Citizen, appCompatImageButton: AppCompatImageButton)
    }
}