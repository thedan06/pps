package com.dustanmbaga.pps.acaricide

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Acaricide represents the Acaricide fetched from the network and stored in the DB
 */
@Entity(tableName = "acaricides")
data class Acaricide(
    @NotNull
    @PrimaryKey
    var id: Int,

    @NotNull
    @ColumnInfo(name = "trade_name")
    var tradeName: String,

    @ColumnInfo(name = "common_name")
    var commonName: String?,

    @ColumnInfo(name = "reg_number")
    var regNumber: String?,

    @ColumnInfo(name = "registrant")
    var registrant: String?,

    @ColumnInfo(name = "usage")
    var usage: String?,

    @ColumnInfo(name = "reg_category")
    var regCategory: String?
)