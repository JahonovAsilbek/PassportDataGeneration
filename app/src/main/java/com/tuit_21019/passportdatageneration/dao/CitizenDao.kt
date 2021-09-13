package com.tuit_21019.passportdatageneration.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tuit_21019.passportdatageneration.entities.Citizen

@Dao
interface CitizenDao {
    @Query("select * from citizen")
    fun getAllCitizens(): List<Citizen>

    @Insert
    fun insertCitizen(citizen: Citizen)

    @Query("UPDATE citizen SET name=:name, surname=:surname,patronomic=:patronomic, region=:region, city=:city,adress=:adress, passportNumber=:passportNumber, givenDate=:givenDate, replacementDate=:replacementDate,gender=:gender,image=:image where id=:id")
    fun updateCitizen(
        name: String,
        surname: String,
        patronomic: String,
        region: String,
        city: String,
        adress: String,
        passportNumber: String,
        givenDate: String,
        replacementDate: String,
        gender: String,
        image: String,
        id:Int
    )

    @Delete
    fun deleteCitizen(citizen: Citizen)

//    @Query("select * from citizen where fuqaro_ismi like \":fuqaro_ismi%\"")
//    fun getCitizensBySearchText(fuqaro_ismi: String):List<Citizen>

}