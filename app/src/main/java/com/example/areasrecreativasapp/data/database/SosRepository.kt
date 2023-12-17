package com.example.areasrecreativasapp.data.database

import kotlinx.coroutines.flow.Flow

interface SosRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllSosStream(): Flow<List<Sos>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getSosStream(id: Int): Flow<Sos>
}