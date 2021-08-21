package com.training.goldmarket.di.app

import android.app.Application
import com.training.goldmarket.BaseApplication
import com.training.goldmarket.di.FragmentModule
import com.training.goldmarket.di.db.DatabaseModule
import com.training.goldmarket.di.preference.PreferenceModule
import com.training.goldmarket.di.repository.RepositoryModule
import com.training.goldmarket.di.ActivitiesModule
import com.training.goldmarket.di.api.ApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApiModule::class,
    RepositoryModule::class,
    PreferenceModule::class,
    DatabaseModule::class,
    ActivitiesModule::class,
    AndroidSupportInjectionModule::class,
    FragmentModule::class
])
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}