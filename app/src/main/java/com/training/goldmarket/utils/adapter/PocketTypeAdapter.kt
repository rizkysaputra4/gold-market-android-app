package com.training.goldmarket.utils.adapter

import android.util.Log
import com.squareup.moshi.*
import com.training.goldmarket.data.entity.PocketType

class PocketTypeAdapter : JsonAdapter<PocketType>() {

    @FromJson
    override fun fromJson(reader: JsonReader): PocketType? {
        return try {
            val string = reader.nextString()
            Log.d("ADAPTER", string)
            PocketType.valueOf(string)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: PocketType?) {
        if (value != null) {
            writer.value(value.name)
        }
    }
}