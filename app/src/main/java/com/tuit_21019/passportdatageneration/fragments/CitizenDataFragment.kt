package com.tuit_21019.passportdatageneration.fragments

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.databinding.FragmentCitizenDataBinding
import com.tuit_21019.passportdatageneration.entities.Citizen
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "citizen"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CitizenDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CitizenDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var citizen: Citizen? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            citizen = it.getSerializable(ARG_PARAM1) as Citizen?
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentCitizenDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitizenDataBinding.inflate(layoutInflater, container, false)
        setToolbar()
        setDataToView()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setDataToView() {
        val imagePath = File(citizen?.fuqaro_rasmi)
        if (imagePath.exists()) {
            val bitMap = BitmapFactory.decodeFile(imagePath.absolutePath)
            binding.image.setImageBitmap(bitMap)
        } else {
            binding.image.setImageResource(R.drawable.ic_baseline_image_24)
        }

        binding.toolbar.title = "${citizen?.ismi} ${citizen?.familyasi}"
        binding.name.text = "Ismi: ${citizen?.ismi}"
        binding.surname.text = "Familiyasi: ${citizen?.familyasi}"
        binding.patronomic.text = "Familiyasi: ${citizen?.otasining_ismi}"
        binding.region.text = "Viloyat: ${citizen?.viloyati}"
        binding.city.text = "Shahar/tuman: ${citizen?.shahar_tuman}"
        binding.adress.text = "Uy manzili: ${citizen?.uyining_manzili}"
        binding.passportNumber.text = "Passport raqami: ${citizen?.passport_raqami}"
        binding.dateOfIssue.text = "Passport berilgan sanasi: ${citizen?.passport_olgan_vaqti}"
        binding.dateOfExpiry.text = "Passport muddati: ${citizen?.passport_muddati}"
        binding.sex.text = "Jinsi: ${citizen?.jinsi}"
    }

    private fun setToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_backbtn)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}