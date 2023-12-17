package com.example.areasrecreativasapp.data.database

import kotlinx.coroutines.flow.Flow

class OfflineAreasRepository(private val areaDao: AreaDao) : AreasRepository {
    override suspend fun insertArea(area: Area) = areaDao.insert(area)

    override fun getAllAreasStream(): Flow<List<Area>> = areaDao.getAllAreas()

    override fun getAreaStream(id: Int): Flow<Area> = areaDao.getArea(id)
}