package com.example.puffandpoof.model

import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone

import android.os.Parcel
import android.os.Parcelable

data class profile(
    val username: String?,
    val email: String?,
    val phoneNumber: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<profile> {
        override fun createFromParcel(parcel: Parcel): profile {
            return profile(parcel)
        }

        override fun newArray(size: Int): Array<profile?> {
            return arrayOfNulls(size)
        }
    }
}

