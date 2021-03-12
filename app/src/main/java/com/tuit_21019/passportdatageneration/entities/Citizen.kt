package com.tuit_21019.passportdatageneration.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "citizen")
class Citizen {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "fuqaro_ismi")
    var ismi: String? = null

    @ColumnInfo(name = "fuqaro_familyasi")
    var familyasi: String? = null

    @ColumnInfo(name = "fuqaro_otasining_ismi")
    var otasining_ismi: String? = null

    var viloyati: String? = null

    var shahar_tuman: String? = null

    var uyining_manzili: String? = null

    var passport_olgan_vaqti: String? = null

    var passport_muddati: String? = null

    var jinsi: String? = null

    var fuqaro_rasmi: String? = null

    @Ignore
    constructor(
        id: Int?,
        ismi: String?,
        familyasi: String?,
        otasining_ismi: String?,
        viloyati: String?,
        shahar_tuman: String?,
        uyining_manzili: String?,
        passport_olgan_vaqti: String?,
        passport_muddati: String?,
        jinsi: String?,
        fuqaro_rasmi: String?
    ) {
        this.id = id
        this.ismi = ismi
        this.familyasi = familyasi
        this.otasining_ismi = otasining_ismi
        this.viloyati = viloyati
        this.shahar_tuman = shahar_tuman
        this.uyining_manzili = uyining_manzili
        this.passport_olgan_vaqti = passport_olgan_vaqti
        this.passport_muddati = passport_muddati
        this.jinsi = jinsi
        this.fuqaro_rasmi = fuqaro_rasmi
    }

    @Ignore
    constructor(
        ismi: String?,
        familyasi: String?,
        otasining_ismi: String?,
        viloyati: String?,
        shahar_tuman: String?,
        uyining_manzili: String?,
        passport_olgan_vaqti: String?,
        passport_muddati: String?,
        jinsi: String?,
        fuqaro_rasmi: String?
    ) {
        this.ismi = ismi
        this.familyasi = familyasi
        this.otasining_ismi = otasining_ismi
        this.viloyati = viloyati
        this.shahar_tuman = shahar_tuman
        this.uyining_manzili = uyining_manzili
        this.passport_olgan_vaqti = passport_olgan_vaqti
        this.passport_muddati = passport_muddati
        this.jinsi = jinsi
        this.fuqaro_rasmi = fuqaro_rasmi
    }

    constructor()
}