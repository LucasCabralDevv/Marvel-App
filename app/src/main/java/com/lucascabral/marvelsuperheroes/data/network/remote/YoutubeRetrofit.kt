package com.lucascabral.marvelsuperheroes.data.network.remote

import com.lucascabral.marvelsuperheroes.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class YoutubeRetrofit {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val PART = "snippet"
        private const val ORDER = "date"
        private const val MAX_RESULTS = "50"
        private const val CHANNEL_ID = "UCItRs-h8YU1wRRfP637614w"
        private const val BASE_URL_YOUTUBE = "https://www.googleapis.com/youtube/v3/"

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val chainRequest = chain.request()
                val urlOriginal = chainRequest.url()
                val httpUrl = urlOriginal.newBuilder()
                    .addQueryParameter("part", PART)
                    .addQueryParameter("order", ORDER)
                    .addQueryParameter("maxResults", MAX_RESULTS)
                    .addQueryParameter("key", BuildConfig.YOUTUBE_KEY)
                    .addQueryParameter("channelId", CHANNEL_ID)
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