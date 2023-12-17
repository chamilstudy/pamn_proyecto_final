package com.example.areasrecreativasapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AreaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(area: Area)

    @Query("SELECT * from areas WHERE id = :id")
    fun getArea(id: Int): Flow<Area>

    @Query("SELECT * from areas")
    fun getAllAreas(): Flow<List<Area>>
}