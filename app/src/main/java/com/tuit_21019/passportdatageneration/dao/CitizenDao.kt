package com.tuit_21019.passportdatageneration.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuit_21019.passportdatageneration.entities.Citizen

@Dao
interface CitizenDao {
    @Query("select * from citizen")
    fun getAllCitizens():ArrayList<Citizen>

    @Insert
    fun insertCitizen(citizen: Citizen)

    @Query("select * from citizen where fuqaro_ismi like \":fuqaro_ismi%\"")
    fun getCitizensBySearchText(fuqaro_ismi: String)
}