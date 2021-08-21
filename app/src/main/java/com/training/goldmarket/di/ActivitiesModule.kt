package com.training.goldmarket.di

import com.training.goldmarket.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}