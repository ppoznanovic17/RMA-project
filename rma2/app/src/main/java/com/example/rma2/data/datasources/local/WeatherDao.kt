package com.example.rma2.data.datasources.local

import androidx.room.*
import com.example.rma2.data.model.local.WeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class WeatherDao {

    @Query("DELETE FROM weather WHERE name = :name")
    abstract fun deleteAll(name: String)

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<WeatherEntity>): Completable


    @Query("SELECT * FROM weather WHERE name = :name")
    abstract fun getAll(name: String): Observable<List<WeatherEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<WeatherEntity>) {
        deleteAll(entities[0].name)
        insertAll(entities).blockingAwait()
    }



}