package com.task.nytimes.Models

import android.os.Parcel
import android.os.Parcelable

class Multimedia() : Parcelable {

//class Multimedia {
    var copyright: String? = null
    var subtype: String? = null
    var format: String? = null
    var width: String? = null
    var caption: String? = null
    var type: String? = null
    var url: String? = null
    var height: String? = null

    constructor(parcel: Parcel) : this() {
        copyright = parcel.readString()
        subtype = parcel.readString()
        format = parcel.readString()
        width = parcel.readString()
        caption = parcel.readString()
        type = parcel.readString()
        url = parcel.readString()
        height = parcel.readString()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(copyright)
        parcel.writeString(subtype)
        parcel.writeString(format)
        parcel.writeString(width)
        parcel.writeString(caption)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Multimedia> {
        override fun createFromParcel(parcel: Parcel): Multimedia {
            return Multimedia(parcel)
        }

        override fun newArray(size: Int): Array<Multimedia?> {
            return arrayOfNulls(size)
        }
    }


}

