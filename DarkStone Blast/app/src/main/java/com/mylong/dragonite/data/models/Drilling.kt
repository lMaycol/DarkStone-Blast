package com.mylong.dragonite.data.models

import android.os.Parcel
import android.os.Parcelable

data class Drilling(
    val title: String,
    val definition: String,
    val concepts: String,
    val relevant: String,
    val principles: String,
    val applications: String,
    val challenges: String,
    val developments: String,
    val conclusion: String,
    val resources: String,
    val image: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(definition)
        parcel.writeString(concepts)
        parcel.writeString(relevant)
        parcel.writeString(principles)
        parcel.writeString(applications)
        parcel.writeString(challenges)
        parcel.writeString(developments)
        parcel.writeString(conclusion)
        parcel.writeString(resources)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Drilling> {
        override fun createFromParcel(parcel: Parcel): Drilling {
            return Drilling(parcel)
        }

        override fun newArray(size: Int): Array<Drilling?> {
            return arrayOfNulls(size)
        }
    }
}