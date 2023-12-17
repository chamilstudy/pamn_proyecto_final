package com.example.inventory

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.data.database.AreaDao
import com.example.areasrecreativasapp.data.database.AreaDatabase
import com.example.areasrecreativasapp.data.database.Sos
import com.example.areasrecreativasapp.data.database.SosDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SosDaoTest {
    private lateinit var sosDao: SosDao
    private lateinit var areaDatabase: AreaDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        areaDatabase = Room.inMemoryDatabaseBuilder(context, AreaDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        sosDao = areaDatabase.sosDao()
    }

    @Test
    @Throws(Exception::class)
    fun datCheck() = runBlocking {
        val retrievedArea = sosDao.getSos(1).first()
        sosDao.insert(Sos(0,"prueba",123))
        val retrievedAreas = sosDao.getAllSos().first()
        //assertNotNull(retrievedArea)
        assertEquals(retrievedAreas[0]?.name, "prueba")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        areaDatabase.close()
    }
}