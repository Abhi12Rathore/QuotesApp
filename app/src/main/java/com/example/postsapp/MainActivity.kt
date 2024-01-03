package com.example.postsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.postsapp.screens.AuthorsScreen
import com.example.postsapp.screens.QuotesScreen
import com.example.postsapp.ui.theme.PostsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostsAppTheme(false) {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = "Quotes")
                    }, backgroundColor = Color.Black, contentColor = Color.White)
                }) {
                    Box(modifier = Modifier.padding(it)) {
                        MyApp()
                    }
                }

            }

        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "authorsList") {
        composable(route = "authorsList") {
            AuthorsScreen(onClick = {
                navController.navigate("quotesList/${it}")
            })
        }
        composable(route = "quotesList/{authorName}", arguments = listOf(navArgument("authorName") {
            type = NavType.StringType
        })) {
            it.arguments?.getString("authorName")?.let { it1 -> QuotesScreen(it1) }
        }
    }
}

