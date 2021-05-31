package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.BuildConfig
import com.lucascabral.marvelsuperheroes.extension.md5
import com.lucascabral.marvelsuperheroes.data.api.MarvelService
import com.lucascabral.marvelsuperheroes.data.api.YoutubeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import java.util.*

val networkMarvelModule = module {

    single(named("BASE_URL")) { "http://gateway.marvel.com/v1/public/" }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)
        client.addInterceptor { chain ->

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
        client.build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(MarvelService::class.java)
    }
}

val networkYoutubeModule = module {
    single(named("BASE_URL_YOUTUBE")) { "https://www.googleapis.com/youtube/v3/" }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->

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
        client.build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_URL_YOUTUBE")))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(YoutubeService::class.java)
    }
}