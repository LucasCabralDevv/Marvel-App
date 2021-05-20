package com.lucascabral.marvelsuperheroes.presenter.di

import com.lucascabral.marvelsuperheroes.extension.md5
import com.lucascabral.marvelsuperheroes.helper.API_KEY
import com.lucascabral.marvelsuperheroes.helper.PRIVATE_KEY
import com.lucascabral.marvelsuperheroes.network.MarvelService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import java.util.*

val networkModule = module {

    single(named("BASE_URL")) { "http://gateway.marvel.com/v1/public/" }

    /*
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        logging
    } */

    single {
        val client = OkHttpClient.Builder()
        //client.addInterceptor(get())
        client.addInterceptor { chain ->

            val chainRequest = chain.request()
            val urlOriginal = chainRequest.url()
            val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
            val httpUrl = urlOriginal.newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", "$ts$PRIVATE_KEY$API_KEY".md5())
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