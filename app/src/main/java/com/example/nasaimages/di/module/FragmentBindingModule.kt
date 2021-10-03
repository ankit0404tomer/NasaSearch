package com.example.nasaimages.di.module

import com.example.nasaimages.di.scope.FragmentScoped
import com.example.nasaimages.ui.DisplayImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun displayImageFragment(): DisplayImageFragment

}
