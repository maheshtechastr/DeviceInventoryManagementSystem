package com.mpg.nagarro.deviceinventorymgmt.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeEntity(
    val name: String,
    @PrimaryKey(autoGenerate = true) val empId: Int,
    val empEmail: String,
) {
    constructor(name: String, empEmail: String) : this(name, 0, empEmail)
}