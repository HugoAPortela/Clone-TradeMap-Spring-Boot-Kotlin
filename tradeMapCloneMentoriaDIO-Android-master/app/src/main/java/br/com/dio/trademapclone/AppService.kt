package br.com.dio.trademapclone

import br.com.dio.trademapclone.domain.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val URL = "http://192.168.1.11:8080"

object RetrofitService {

    private fun criarHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    val retrofit = Retrofit.Builder()
        .client(criarHttpClient())
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createService() = retrofit.create(ApiService::class.java)

}