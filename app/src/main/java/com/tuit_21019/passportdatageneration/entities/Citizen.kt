package com.tuit_21019.passportdatageneration.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Citizen(
    @PrimaryKey(autoGenerate = true) @Ignore var id: Int,
    @ColumnInfo(name = "fuqaro_ismi") var ismi: String,
    @ColumnInfo(name = "fuqaro_familyasi") var familyasi: String,
    @ColumnInfo(name = "fuqaro_otasining_ismi") var otasining_ismi: String,
    var viloyati: String,
    var shahar_tuman: String,
    var uyining_manzili: String,
    var passport_olgan_vaqti: String,
    var passport_muddati: String,
    var jinsi: String,
    var fuqaro_rasmi: String
)