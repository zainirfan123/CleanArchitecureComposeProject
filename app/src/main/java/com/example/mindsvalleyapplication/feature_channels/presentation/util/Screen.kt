package com.example.mindsvalleyapplication.feature_channels.presentation.util

sealed class Screen(val route:String){
    object Channels: Screen("channels_screen")
}
