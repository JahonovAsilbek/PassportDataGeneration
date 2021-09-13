package com.tuit_21019.passportdatageneration.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.adapters.PassportAdapter
import com.tuit_21019.passportdatageneration.dao.CitizenDao
import com.tuit_21019.passportdatageneration.database.AppDatabase
import com.tuit_21019.passportdatageneration.databinding.FragmentSearchBinding
import com.tuit_21019.passportdatageneration.entities.Citizen

class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.get.getDatabase()
        getDao = database.citizenDao()
        adapter = PassportAdapter()
    }

    lateinit var binding: FragmentSearchBinding
    lateinit var data: ArrayList<Citizen>
    lateinit var database: AppDatabase
    lateinit var getDao: CitizenDao
    lateinit var adapter: PassportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, null, false)

        binding.searchView.requestFocus()
        binding.searchView.isFocusableInTouchMode = true
        val imm: InputMethodManager? =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(binding.searchView, InputMethodManager.SHOW_IMPLICIT)

        loadData()
        loadAdapter()
        itemClick()
        searchText()
        cancelClick()

        return binding.root
    }

    private fun searchText() {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }


    private fun filter(text: String) {
        val filteredList = ArrayList<Citizen>()
        for (item in data) {
            if (item.name!!.lowercase().contains(text.lowercase()) || item.surname
                !!.lowercase().contains(text.lowercase())
            ) {
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }

    private fun cancelClick() {
        binding.cancelSearching.setOnClickListener {
            if (binding.searchView.text.isNotEmpty()) {
                binding.searchView.setText(null)
            } else {
                findNavController().popBackStack()
            }
        }
    }

    private fun itemClick() {
        adapter.onItemClick = object : PassportAdapter.OnItemClick {
            override fun onClick(citizen: Citizen) {
                val bundle = Bundle()
                bundle.putSerializable("citizen", citizen)
                findNavController().navigate(R.id.citizenDataFragment, bundle)
            }

            override fun onPopupClick(
                citizen: Citizen,
                appCompatImageButton: AppCompatImageButton
            ) {
                val popupMenu = PopupMenu(binding.root.context, appCompatImageButton)
                popupMenu.inflate(R.menu.popup)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.edit -> {
                            val bundle = Bundle()
                            bundle.putSerializable("citizen", citizen)
                            findNavController().navigate(R.id.addPasportDataFragment, bundle)
                        }
                        R.id.delete -> {
                            val dialog = AlertDialog.Builder(binding.root.context)
                            dialog.setTitle("O'chirish")
                            dialog.setMessage("Rostdan ham ushbu ma'lumotni o'chirmoqchimisiz?")
                            dialog.setPositiveButton(
                                "Ha"
                            ) { dialog, which ->
                                getDao.deleteCitizen(citizen)
                                loadData()
                                loadAdapter()
                                dialog?.cancel()
                            }
                            dialog.setNegativeButton(
                                "Yo'q"
                            ) { dialog, which -> dialog?.cancel() }
                            dialog.show()
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        }
    }

    private fun loadAdapter() {
        adapter.setAdapter(data)
        binding.citizenRv.adapter = adapter
    }

    private fun loadData() {
        data = getDao.getAllCitizens() as ArrayList<Citizen>
    }

}