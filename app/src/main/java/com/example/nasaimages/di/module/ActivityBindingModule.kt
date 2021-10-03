package com.example.nasaimages.di.module

import com.example.nasaimages.di.scope.ActivityScoped
import com.example.nasaimages.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(
    includes = [ViewModelModule::class]
)

abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}
