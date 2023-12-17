package com.example.areasrecreativasapp.states

import com.example.areasrecreativasapp.data.database.Area

data class AreasState (
    val areasList: List<Area> = emptyList()
)
