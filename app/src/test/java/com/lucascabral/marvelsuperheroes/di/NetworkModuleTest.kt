package com.lucascabral.marvelsuperheroes.di

import com.lucascabral.marvelsuperheroes.network.MarvelService
import com.lucascabral.marvelsuperheroes.presenter.di.networkModule
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import retrofit2.Retrofit

class NetworkModuleTest : KoinTest {

    private val api: MarvelService by inject()
    private val retrofit: Retrofit by inject()
    private val okHttpClient: OkHttpClient by inject()
    private val moshi: Moshi by inject()
    private val baseUrl: String by lazy { get(named("BASE_URL")) as String }

    @Before
    fun setup() {
        startKoin { modules(listOf(networkModule)) }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Test Retrofit Created`() {
        assertNotNull(retrofit)
        assert(baseUrl == "http://gateway.marvel.com/v1/public/")
    }

    @Test
    fun `Test Okhttp`() {
        assertNotNull(okHttpClient)
    }

    @Test
    fun `Test Api Created`() {
        assertNotNull(api)
    }

    @Test
    fun `Test Moshi Created`() {
        assertNotNull(moshi)
    }
}