package com.example.nasaimages.network.rx

import io.reactivex.functions.Function

interface Converter<T, R> : Function<T, R>
