package com.example.prm392_project.util;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ConvertHelper {
    @TypeConverter
    public static LocalDateTime fromTimestamp(Long value) {
        return value == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC);
    }

    @TypeConverter
    public static Long toTimestamp(LocalDateTime date) {
        return date == null ? null : date.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
