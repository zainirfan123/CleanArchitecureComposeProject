package com.example.mindsvalleyapplication.feature_channels.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mindsvalleyapplication.feature_channels.presentation.ChannelScreen
import com.example.mindsvalleyapplication.feature_channels.presentation.util.Screen
import com.example.mindsvalleyapplication.ui.theme.MindsValleyApplicationTheme
import com.example.mindsvalleyapplication.utils.Extensions.isInternetConnected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MindsValleyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Navigation()
        }
      }
    }
  }
}

@Composable
fun Navigation() {
  val context = LocalContext.current
  val navController = rememberNavController()
  val isFetchFromDB = context.isInternetConnected()
  NavHost(
    navController = navController,
    startDestination = Screen.Channels.route + "?isFetchFromDB=$isFetchFromDB"
  ) {
    composable(
      route = Screen.Channels.route + "?isFetchFromDB={isFetchFromDB}",
      arguments = listOf(navArgument("isFetchFromDB") { type = NavType.BoolType })
    ) {
      ChannelScreen(navController = navController)
    }
  }
}
