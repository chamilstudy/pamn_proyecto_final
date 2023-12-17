package com.example.areasrecreativasapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sos: Sos)

    @Query("SELECT * from sos_info WHERE id = :id")
    fun getSos(id: Int): Flow<Sos>

    @Query("SELECT * from sos_info")
    fun getAllSos(): Flow<List<Sos>>
}