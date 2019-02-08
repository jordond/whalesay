package ca.hoogit.whalesay.data.api

import ca.hoogit.whalesay.data.api.googlecloud.GoogleCloudAPIModule
import ca.hoogit.whalesay.data.api.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        GoogleCloudAPIModule::class
    ]
)
abstract class APIModule
