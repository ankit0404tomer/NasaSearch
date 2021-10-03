package com.example.nasaimages.di.module

import androidx.lifecycle.ViewModel
import com.example.nasaimages.di.scope.ViewModelScoped
import com.example.nasaimages.ui.DisplayImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 *<p>
 * The ViewModelModule is used to provide a mapData of view models through dagger that is used by the ViewModelFactoryModule class.
 *</p>
 */

@Module
abstract class ViewModelModule : ViewModelFactoryModule() {

    @Binds
    @IntoMap
    @ViewModelScoped(DisplayImageViewModel::class)
    internal abstract fun displayImageViewModelViewModel(viewModel: DisplayImageViewModel): ViewModel


}
