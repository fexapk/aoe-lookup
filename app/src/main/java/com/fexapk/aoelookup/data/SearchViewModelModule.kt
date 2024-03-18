package com.fexapk.aoelookup.data

import android.content.Context
import com.fexapk.aoelookup.network.AoeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
internal object SearchViewModelModule {

    @Provides
    @ViewModelScoped
    fun providePlayerRepo(@ApplicationContext context: Context) : PlayerRepository {

        val baseUrl = "https://aoe4world.com/api/v0/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return NetworkPlayerRepository(
            service = retrofit.create(AoeApiService::class.java)
        )
    }
}
