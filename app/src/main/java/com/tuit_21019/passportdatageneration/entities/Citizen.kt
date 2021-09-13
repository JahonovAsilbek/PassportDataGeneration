package com.tuit_21019.passportdatageneration.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "citizen")
class Citizen : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var name: String? = null

    var surname: String? = null

    var patronomic: String? = null

    var region: String? = null

    var city: String? = null

    var adress: String? = null

    var passportNumber: String? = null

    var givenDate: String? = null

    var replacementDate: String? = null

    var gender: String? = null

    var image: String? = null


    constructor()

    @Ignore
    constructor(
        id: Int?,
        name: String?,
        surname: String?,
        patronomic: String?,
        region: String?,
        city: String?,
        adress: String?,
        passportNumber: String?,
        givenDate: String?,
        replacementDate: String?,
        gender: String?,
        image: String?
    ) {
        this.id = id
        this.name = name
        this.surname = surname
        this.patronomic = patronomic
        this.region = region
        this.city = city
        this.adress = adress
        this.passportNumber = passportNumber
        this.givenDate = givenDate
        this.replacementDate = replacementDate
        this.gender = gender
        this.image = image
    }

    @Ignore
    constructor(
        name: String?,
        surname: String?,
        patronomic: String?,
        region: String?,
        city: String?,
        adress: String?,
        passportNumber: String?,
        givenDate: String?,
        replacementDate: String?,
        gender: String?,
        image: String?
    ) {
        this.name = name
        this.surname = surname
        this.patronomic = patronomic
        this.region = region
        this.city = city
        this.adress = adress
        this.passportNumber = passportNumber
        this.givenDate = givenDate
        this.replacementDate = replacementDate
        this.gender = gender
        this.image = image
    }


}