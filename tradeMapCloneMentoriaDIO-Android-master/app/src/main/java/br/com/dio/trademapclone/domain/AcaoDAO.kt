package br.com.dio.trademapclone.domain

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AcaoDAO {

    @Query("SELECT * FROM Acao ORDER BY id DESC LIMIT 1")
    fun getUltimo(): LiveData<Acao>

    @Query("SELECT * FROM Acao WHERE codigo = :codigo ORDER BY id DESC LIMIT 1")
    fun getUltimo(codigo: String): LiveData<Acao>

    @Query("SELECT * FROM Acao WHERE codigo = :codigo")
    suspend fun getTodos(codigo: String): List<Acao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(company: Acao): Long

}