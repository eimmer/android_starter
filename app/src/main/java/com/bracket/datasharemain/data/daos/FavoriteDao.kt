package com.bracket.datasharemain.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bracket.datasharemain.data.entities.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>

    @Insert
    fun insert(vararg favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}