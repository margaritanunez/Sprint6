package com.example.sprint6.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneEntity::class, DetailPhoneEntity::class], version = 1)
abstract class PhonesDataBase : RoomDatabase() {
    abstract fun getPhoneDao() : PhoneDao

    companion object{

        @Volatile

        private var INSTANCE: PhonesDataBase? = null

        fun getDatabase(context: Context): PhonesDataBase{
            val tempInstance = INSTANCE
            if (tempInstance !=null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhonesDataBase::class.java,
                    "razas_database"
                ).build()

                INSTANCE = instance
                return instance

            }
        }
    }
}