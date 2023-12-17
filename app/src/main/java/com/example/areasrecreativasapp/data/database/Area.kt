package com.example.areasrecreativasapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "areas")
data class Area(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "security")
    val security: Boolean,
    @ColumnInfo(name = "parkingforcars")
    val parkingforcars: Boolean,
    @ColumnInfo(name = "parkingforcampers")
    val parkingforcampers: Boolean,
    @ColumnInfo(name = "bathrooms")
    val bathrooms: Boolean,
    @ColumnInfo(name = "water")
    val water: Boolean,
    @ColumnInfo(name = "furnace")
    val furnace: Boolean,
    @ColumnInfo(name = "picnic")
    val picnic: Boolean,
    @ColumnInfo(name = "capacitypeople")
    val capacitypeople: Int,
    @ColumnInfo(name = "capacitycampers")
    val capacitycampers: Int,
    @ColumnInfo(name = "capacitytents")
    val capacitytents: Int,
    @ColumnInfo(name = "isavailable")
    val isavailable: Boolean,
    @ColumnInfo(name = "img")
    val img: String
)

