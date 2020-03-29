package com.dustanmbaga.pps.acaricide

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

/**
 * Acaricide represents the Acaricide fetched from the network and stored in the DB
 */
@Entity(tableName = "acaricides")
@Parcelize
data class Acaricide(
    @NotNull
    @SerializedName("id")
    @PrimaryKey
    var id: Int? = 0,

    @NotNull
    @SerializedName("trade_name")
    @ColumnInfo(name = "trade_name")
    var tradeName: String? = "",

    @SerializedName("common_name")
    @ColumnInfo(name = "common_name")
    var commonName: String? = "",

    @SerializedName("reg_number")
    @ColumnInfo(name = "reg_number")
    var regNumber: String? = "",

    @SerializedName("registrant")
    @ColumnInfo(name = "registrant")
    var registrant: String? = "",

    @SerializedName("usage")
    @ColumnInfo(name = "usage")
    var usage: String? = "",

    @SerializedName("reg_category")
    @ColumnInfo(name = "reg_category")
    var regCategory: String? = ""
): Parcelable {
    constructor() : this(0, "", "", "", "", "", "")
}