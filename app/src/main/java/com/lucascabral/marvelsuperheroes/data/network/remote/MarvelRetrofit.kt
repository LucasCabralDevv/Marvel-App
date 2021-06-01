package com.lucascabral.marvelsuperheroes.data.network.remote

import com.lucascabral.marvelsuperheroes.BuildConfig
import com.lucascabral.marvelsuperheroes.extension.md5
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class MarvelRetrofit {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val chainRequest = chain.request()
                val urlOriginal = chainRequest.url()
                val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                val httpUrl = urlOriginal.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}".md5())
                    .build()

                chain.proceed(chainRequest.newBuilder().url(httpUrl).build())
            }
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}