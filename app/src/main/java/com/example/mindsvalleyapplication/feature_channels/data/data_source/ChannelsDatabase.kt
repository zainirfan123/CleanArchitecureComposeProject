package com.example.mindsvalleyapplication.feature_channels.data.data_source

import androidx.room.RoomDatabase

/*@Database(
    entities =
        [Category::class],
    version = 3)*/
abstract class ChannelsDatabase : RoomDatabase() {

  /*abstract fun categoryDao(): CategoryDao

  abstract fun channelDao(): ChannelDao

  companion object {
    @Volatile private var INSTANCE: ChannelsDatabase? = null

    fun getInstance(context: Context): ChannelsDatabase {
      return INSTANCE
          ?: synchronized(this) {
            val instance =
                Room.databaseBuilder(
                        context.applicationContext, ChannelsDatabase::class.java, "app_database")
                    .build()
            INSTANCE = instance
            instance
          }
    }
  }*/
}
