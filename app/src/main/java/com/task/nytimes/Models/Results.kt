package com.task.nytimes.Models

import android.os.Parcel
import android.os.Parcelable
class Results() : Parcelable {


     var per_facet: List<String>? = null
    var subsection: String? = null
    var item_type: String? = null
     var org_facet: List<String>? = null
    var section: String? = null
    var abstract: String? = null
    var title: String? = null
     var des_facet: List<String>? = null
    var uri: String? = null
    var url: String? = null
    var short_url: String? = null
    var material_type_facet: String? = null
     var multimedia: ArrayList<Multimedia>? = null
     var geo_facet:List<String>? = null
    var updated_date: String? = null
    var created_date: String? = null
    var byline: String? = null
    var published_date: String? = null
    var kicker: String? = null

    constructor(parcel: Parcel) : this() {
        per_facet = parcel.createStringArrayList()
        subsection = parcel.readString()
        item_type = parcel.readString()
        org_facet = parcel.createStringArrayList()
        section = parcel.readString()
        abstract = parcel.readString()
        title = parcel.readString()
        des_facet = parcel.createStringArrayList()
        uri = parcel.readString()
        url = parcel.readString()
        short_url = parcel.readString()
        material_type_facet = parcel.readString()
        geo_facet = parcel.createStringArrayList()
        updated_date = parcel.readString()
        created_date = parcel.readString()
        byline = parcel.readString()
        published_date = parcel.readString()
        kicker = parcel.readString()
        this.multimedia = ArrayList()

        parcel.readList(this.multimedia, Multimedia::class.java.getClassLoader())

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(per_facet)
        parcel.writeString(subsection)
        parcel.writeString(item_type)
        parcel.writeStringList(org_facet)
        parcel.writeString(section)
        parcel.writeString(abstract)
        parcel.writeString(title)
        parcel.writeStringList(des_facet)
        parcel.writeString(uri)
        parcel.writeString(url)
        parcel.writeString(short_url)
        parcel.writeString(material_type_facet)
        parcel.writeStringList(geo_facet)
        parcel.writeString(updated_date)
        parcel.writeString(created_date)
        parcel.writeString(byline)
        parcel.writeString(published_date)
        parcel.writeString(kicker)
        parcel.writeList(this.multimedia); //  parcel.createTypedArrayList()

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Results> {
        override fun createFromParcel(parcel: Parcel): Results {
            return Results(parcel)
        }

        override fun newArray(size: Int): Array<Results?> {
            return arrayOfNulls(size)
        }
    }
}
