package com.example.nasaimages.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 *<p>
 * BaseAppViewModelFactory class basically helps you dynamically create ViewModels for your Activities and Fragments.
 * The ViewModelFactoryclass has a list of providers and can create any ViewModel that was bound.
 * Fragments and Activities can now just inject the factory and retrieve their ViewModel
 *</p>
 */
@Singleton
class BaseAppViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

        try {
            @Suppress("UNCHECKED_CAST") return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}
