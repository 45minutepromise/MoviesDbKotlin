package com.mobile.app.themovies.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mobile.app.themovies.data.db.entities.MovieDetailsEntity
import io.reactivex.Single

@Dao
interface MovieDetailsDao {
    @Query("DELETE FROM movie_details")
    fun deleteAll()

    @Query("SELECT * FROM movie_details where id = :id")
    fun getMovieDetails(id: Long): Single<MovieDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetails(detailsEntity: MovieDetailsEntity)
}