package com.example.cleanarchitecture.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.presentation.coinDetail.components.CoinDetailScreen
import com.example.cleanarchitecture.presentation.coinList.components.CoinListScreen
import com.example.cleanarchitecture.presentation.coinList.viewmdoel.CoinListViewModel
import com.example.cleanarchitecture.presentation.ui.theme.CleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                   NavHost(
                       navController = navController,
                       startDestination = Screen.CoinDetailScreen.route
                   ){
                       composable(
                           route = Screen.CoinListScreen.route
                       ){
                           CoinListScreen(navController = navController)
                       }
                       composable(
                           route = Screen.CoinDetailScreen.route
                       ){
                           CoinDetailScreen()
                       }
                   }
                    
                }
            }
        }
    }
}
