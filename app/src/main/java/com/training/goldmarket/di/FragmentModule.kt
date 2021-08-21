package com.training.goldmarket.di

import com.training.goldmarket.presentation.history.HistoryFragment
import com.training.goldmarket.presentation.home.HomeFragment
import com.training.goldmarket.presentation.home.createpocket.CreatePocketFragment
import com.training.goldmarket.presentation.login.LoginFragment
import com.training.goldmarket.presentation.profile.ProfileFragment
import com.training.goldmarket.presentation.register.RegisterFragment
import com.training.goldmarket.presentation.welcome.SplashFragment
import com.training.goldmarket.presentation.welcome.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeWelcomeFragment(): WelcomeFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCreatePocketFragment(): CreatePocketFragment

    @ContributesAndroidInjector
    abstract fun contributeHistoryPocketFragment(): HistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment
}