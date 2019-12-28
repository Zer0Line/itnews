package com.news.it.db

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromDouble(value: Double): BigDecimal {
        return BigDecimal(value)
    }

    @TypeConverter
    fun toDouble(value: BigDecimal): Double {
        return value.toDouble()
    }
}