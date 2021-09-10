package com.tuit_21019.passportdatageneration.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.google.android.material.snackbar.Snackbar
import com.tuit_21019.passportdatageneration.BuildConfig
import com.tuit_21019.passportdatageneration.R
import com.tuit_21019.passportdatageneration.adapters.MySpinnerAdapter
import com.tuit_21019.passportdatageneration.dao.CitizenDao
import com.tuit_21019.passportdatageneration.database.AppDatabase
import com.tuit_21019.passportdatageneration.databinding.FragmentAddPasportDataBinding
import com.tuit_21019.passportdatageneration.entities.Citizen
import kotlinx.android.synthetic.main.camera_or_gallery_dialog.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AddPasportDataFragment : Fragment() {
    lateinit var binding: FragmentAddPasportDataBinding
    var viloyatList: ArrayList<String>? = null
    var jinsList: ArrayList<String>? = null

    lateinit var db: CitizenDao
    lateinit var dialog_view: View
    lateinit var dialog: AlertDialog

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
        imageClick()

        db = AppDatabase.get.getDatabase().citizenDao()
        dialog_view = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.camera_or_gallery_dialog, null, false)
        photoUri =
            FileProvider.getUriForFile(binding.root.context, BuildConfig.APPLICATION_ID, imageFile)


        setCameraClick()
        setGalleryClick()
        saveClick()
        return binding.root
    }

    private fun saveClick() {
        binding.saqlashBtn.setOnClickListener {
            val ismi = binding.fuqaroIsmiEt.text.toString().trim()
            val familyasi: String = binding.fuqaroFamilyasiEt.text.toString().trim()
            val otasining_ismi = binding.fuqaroOtasiningIsmiEt.text.toString().trim()
            val viloyati = viloyatList!![binding.viloyatiSpinner.selectedItemPosition]
            val shahar_tuman = binding.shaharTumanEt.text.toString().trim()
            val uyining_manzili = binding.uyiningManziliEt.text.toString().trim()
            val passport_olgan_vaqti = binding.passportOlganVaqtiEt.text.toString().trim()
            val passport_muddati = binding.passportMuddatiEt.text.toString().trim()

            val r = Random()
            val passport_seriya_raqami = "AC " + (r.nextInt(9999999 - 1000000) + 1000000)

            Log.d("AAAA", "seriya: $passport_seriya_raqami")

            val jinsi = jinsList!![binding.jinsiSpinner.selectedItemPosition]
            if (ismi != "" && familyasi != "" && otasining_ismi != "" && viloyati != "" && shahar_tuman != ""
                && uyining_manzili != "" && passport_olgan_vaqti != "" && passport_muddati != ""
                && passport_seriya_raqami != "" && jinsi != "" && image_path != ""
            ) {

                db.insertCitizen(
                    Citizen(
                        ismi,
                        familyasi,
                        otasining_ismi,
                        viloyati,
                        shahar_tuman,
                        uyining_manzili,
                        passport_seriya_raqami,
                        passport_olgan_vaqti,
                        passport_muddati,
                        jinsi,
                        image_path
                    )
                )

                Snackbar.make(binding.root, "Muvaffaqiyatli qo'shildi", Snackbar.LENGTH_LONG).show()
                findNavController().popBackStack()
            } else {
                Snackbar.make(binding.root, "Barcha maydonlarni to'ldiring!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
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
        binding.jinsiSpinner.adapter = jinsAdapter

    }

    private var image_path = ""

    //upload image from GALLERY
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            binding.selectImage.setImageURI(uri)
            val ins = activity?.contentResolver?.openInputStream(uri)
            var son = 0
            if (db.getAllCitizens().size != 0) {
                son = db.getAllCitizens()[db.getAllCitizens().size - 1].id!!
            }
            val file = File(activity?.filesDir, "imageNew${son}.jpg")
            val fileOutputStream = FileOutputStream(file)
            ins?.copyTo(fileOutputStream)
            ins?.close()
            fileOutputStream.close()
            image_path = file.absolutePath
        }

    private fun setGalleryClick() {
        dialog_view.choose_gallery_btn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    binding.root.context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED

            ) {
                getImageContent.launch("image/*")
            }
            dialog.cancel()
        }
    }


    private fun imageClick() {
        binding.selectImage.setOnClickListener {
            askPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ) {
                showCameraGalleryDialog()
            }.onDeclined { e ->
                if (e.hasDenied()) {
                    AlertDialog.Builder(binding.root.context)
                        .setMessage("Please accept our permissions")
                        .setPositiveButton("yes") { dialog, which ->
                            e.askAgain();
                        } //ask again
                        .setNegativeButton("no") { dialog, which ->
                            dialog.dismiss();
                        }
                        .show();
                }
                if (e.hasForeverDenied()) {
                    e.goToSettings();
                }
            }
        }
    }

    private fun showCameraGalleryDialog() {
        dialog = AlertDialog.Builder(binding.root.context).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.selectImage.setOnClickListener {
            dialog_view.dialog_bekor_qilish.setOnClickListener {
                dialog.cancel()
                dialog.dismiss()
            }
            dialog.setView(dialog_view)

            dialog.show()
        }
    }


    val imageFile = createImageFile()
    lateinit var photoUri: Uri
    lateinit var currentPhotoPath: String

    //upload image from CAMERA
    private val getCameraImage = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            binding.selectImage.setImageURI(photoUri)
            val ins = activity?.contentResolver?.openInputStream(photoUri)
            var son = 0
            if (db.getAllCitizens().size != 0) {
                son = db.getAllCitizens()[db.getAllCitizens().size - 1].id!!
            }
            val file = File(activity?.filesDir, "imageNew${son}.jpg")
            val fileOutputStream = FileOutputStream(file)
            ins?.copyTo(fileOutputStream)
            ins?.close()
            fileOutputStream.close()
            image_path = file.absolutePath
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun setCameraClick() {
        dialog_view.choose_camera_btn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    binding.root.context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

            ) {
                getCameraImage.launch(photoUri)
            }
            dialog.cancel()
        }
    }
}