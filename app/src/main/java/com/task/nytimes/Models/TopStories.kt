package com.task.nytimes.Models

import android.os.Parcel
import android.os.Parcelable

class TopStories() : Parcelable {

    var copyright: String? = null
    var last_updated: String? = null
    var section: String? = null
    var results: ArrayList<Results>? = null
    var num_results: String? = null
    var status: String? = null

    constructor(parcel: Parcel) : this() {
        copyright = parcel.readString()
        last_updated = parcel.readString()
        section = parcel.readString()
        num_results = parcel.readString()
        status = parcel.readString()
        this.results = ArrayList()
        parcel.readList(this.results, Results::class.java.getClassLoader())

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(copyright)
        parcel.writeString(last_updated)
        parcel.writeString(section)
        parcel.writeString(num_results)
        parcel.writeString(status)
        parcel.writeList(this.results);
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopStories> {
        override fun createFromParcel(parcel: Parcel): TopStories {
            return TopStories(parcel)
        }

        override fun newArray(size: Int): Array<TopStories?> {
            return arrayOfNulls(size)
        }
    }


}