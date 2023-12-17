package com.example.inventory

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.data.database.AreaDao
import com.example.areasrecreativasapp.data.database.AreaDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AreaDaoTest {
    private lateinit var areaDao: AreaDao
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
        areaDao = areaDatabase.areaDao()
    }

    @Test
    @Throws(Exception::class)
    fun datCheck() = runBlocking {
        val retrievedArea = areaDao.getArea(1).first()
        areaDao.insert(Area(5,"prueba", "prueba", true, true, true, true, true, true, true, 0, 0 ,0, false, "img"))
        val retrievedAreas = areaDao.getAllAreas().first()
        //assertNotNull(retrievedArea)
        assertEquals(retrievedAreas[0]?.name, "prueba")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        areaDatabase.close()
    }
}