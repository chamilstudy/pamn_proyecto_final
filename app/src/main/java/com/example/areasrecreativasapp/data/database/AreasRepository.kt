package com.example.areasrecreativasapp.data.database

import kotlinx.coroutines.flow.Flow

interface AreasRepository {
    /**
     * Insert item in the data source
     */
    suspend fun insertArea(area: Area)

    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllAreasStream(): Flow<List<Area>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getAreaStream(id: Int): Flow<Area>
}