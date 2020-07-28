package com.potter.triwizard.injection

import com.potter.triwizard.BuildConfig
import com.potter.triwizard.network.TwizardApi
import com.potter.triwizard.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(

    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(logging)
        okHttpClientBuilder.addInterceptor { chain ->
            val url = chain
                .request()
                .url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        }
       // okHttpClientBuilder.addInterceptor(AuthInteceptor())
        return okHttpClientBuilder
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(TwizardApi::class.java)

    @Provides
    fun provideHouseRepository(twizardApi: TwizardApi): HouseRepository {
        return HouseRepositoryImp(twizardApi)
    }
    @Provides
    fun provideSpellRepository(twizardApi: TwizardApi): SpellRepository {
        return SpellRepositoryImpl(twizardApi)
    }
    @Provides
    fun provideStudentRepository(twizardApi: TwizardApi): StudentRepository {
        return StudentRepositoryImpl(twizardApi)
    }


}
