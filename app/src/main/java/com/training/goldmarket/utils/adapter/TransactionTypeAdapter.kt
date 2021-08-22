package com.training.goldmarket.utils.adapter

import com.squareup.moshi.*
import com.training.goldmarket.data.entity.TransactionType

class TransactionTypeAdapter : JsonAdapter<TransactionType>() {

    @FromJson
    override fun fromJson(reader: JsonReader): TransactionType? {
        return try {
            TransactionType.valueOf(reader.nextString())
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: TransactionType?) {
        if (value != null) {
            writer.value(value.name)
        }
    }
}