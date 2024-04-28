package com.g59939.mobg6

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.g59939.mobg6.ui.AboutScreen
import com.g59939.mobg6.ui.AppViewModel
import com.g59939.mobg6.ui.LoginScreen
import com.g59939.mobg6.ui.LogoScreen
import com.g59939.mobg6.ui.theme.Mobg6Theme

enum class NavRoutes {
    Login,
    Logo,
    About,
}

@Composable
fun He2bApp(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel = viewModel()
) {
    Scaffold(
        bottomBar = {
            if (appViewModel.show_bottom_bar) {
                BottomAppBar {
                    BottomBar(navController = navController)
                }
            }
        },
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Login.name,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = NavRoutes.Login.name) {
                    appViewModel.show_bottom_bar = false
                    LoginScreen(appViewModel = appViewModel, loginButtonClick = {
                        navController.navigate(NavRoutes.Logo.name)
                    })
                }
                composable(route = NavRoutes.Logo.name) {
                    appViewModel.show_bottom_bar = true
                    appViewModel.homeButton = false
                    appViewModel.aboutButton = true
                    LogoScreen()
                }
                composable(route = NavRoutes.About.name) {
                    appViewModel.show_bottom_bar = true
                    appViewModel.homeButton = true
                    appViewModel.aboutButton = false
                    AboutScreen()
                }
            }
        }

    }
}

@Composable
fun BottomBar(navController: NavHostController, appViewModel: AppViewModel = viewModel()) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                navController.navigate(NavRoutes.Logo.name)
            },
            enabled = appViewModel.homeButton
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Button(
            onClick = {
                navController.navigate(NavRoutes.About.name)
            },
            enabled = appViewModel.aboutButton
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    Mobg6Theme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            He2bApp()
        }
    }
}