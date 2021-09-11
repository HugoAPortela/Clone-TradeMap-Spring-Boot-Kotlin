package br.com.dio.trademapclone.domain

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/usuarios/{login}")
    suspend fun login(@Path("login") login: String): Usuario

    @POST("/acoes/favorita")
    suspend fun adicionarFavorito(@Body acaoFavorita: AcaoFavorita): Acao

}