package com.worldturtlemedia.whalesay.data.api

import com.worldturtlemedia.whalesay.data.api.googlecloud.GoogleCloudAPIModule
import com.worldturtlemedia.whalesay.data.api.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        GoogleCloudAPIModule::class
    ]
)
abstract class APIModule
