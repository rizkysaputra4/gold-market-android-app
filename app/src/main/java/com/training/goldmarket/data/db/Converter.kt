package com.training.goldmarket.data.db

import androidx.room.TypeConverter
import com.training.goldmarket.data.entity.PocketType
import com.training.goldmarket.data.entity.TransactionType
import java.util.*

class Converter {

    @TypeConverter
    fun fromPocketType(pocketType: PocketType): String {
        return pocketType.name
    }

    @TypeConverter
    fun toPocketType(pocketType: String): PocketType {
        return PocketType.valueOf(pocketType)
    }

    @TypeConverter
    fun fromTransactionType(transactionType: TransactionType): String {
        return transactionType.name
    }

    @TypeConverter
    fun toTransactionType(transactionType: String): TransactionType {
        return TransactionType.valueOf(transactionType)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}