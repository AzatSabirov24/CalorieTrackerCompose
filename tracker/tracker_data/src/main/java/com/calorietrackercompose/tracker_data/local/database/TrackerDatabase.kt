package com.calorietrackercompose.tracker_data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calorietrackercompose.tracker_data.local.dao.TrackerDao
import com.calorietrackercompose.tracker_data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase : RoomDatabase() {

    abstract val dao: TrackerDao
}