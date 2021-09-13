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
        val imagePath = File(citizen?.image)
        if (imagePath.exists()) {
            val bitMap = BitmapFactory.decodeFile(imagePath.absolutePath)
            binding.image.setImageBitmap(bitMap)
        } else {
            binding.image.setImageResource(R.drawable.ic_baseline_image_24)
        }

        binding.toolbar.title = "${citizen?.name} ${citizen?.surname}"
        binding.name.text = "Ismi: ${citizen?.name}"
        binding.surname.text = "Familiyasi: ${citizen?.surname}"
        binding.patronomic.text = "Familiyasi: ${citizen?.patronomic}"
        binding.region.text = "Viloyat: ${citizen?.region}"
        binding.city.text = "Shahar/tuman: ${citizen?.city}"
        binding.adress.text = "Uy manzili: ${citizen?.adress}"
        binding.passportNumber.text = "Passport raqami: ${citizen?.passportNumber}"
        binding.dateOfIssue.text = "Passport berilgan sanasi: ${citizen?.givenDate}"
        binding.dateOfExpiry.text = "Passport muddati: ${citizen?.replacementDate}"
        binding.sex.text = "Jinsi: ${citizen?.gender}"
    }

    private fun setToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_left_arrow)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}