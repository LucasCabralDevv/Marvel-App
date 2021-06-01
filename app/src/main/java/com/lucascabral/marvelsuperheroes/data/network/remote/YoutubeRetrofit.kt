package com.lucascabral.marvelsuperheroes.data.network.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class YoutubeRetrofit {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val BASE_URL_YOUTUBE = "https://www.googleapis.com/youtube/v3/"

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val chainRequest = chain.request()
                val urlOriginal = chainRequest.url()
                val httpUrl = urlOriginal.newBuilder()
                    .addQueryParameter("part", "snippet")
                    .addQueryParameter("order", "date")
                    .addQueryParameter("maxResults", "20")
                    .addQueryParameter("key", "AIzaSyBJZ_BdXITaCdT7Cyz4VY8CkZjn-89FNng")
                    .addQueryParameter("channelId", "UCItRs-h8YU1wRRfP637614w")
                    .build()

                chain.proceed(chainRequest.newBuilder().url(httpUrl).build())
            }
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_YOUTUBE)
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