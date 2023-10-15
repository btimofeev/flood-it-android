package org.emunix.floodit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.emunix.floodit.ui.game.FloodItApp
import org.emunix.floodit.ui.theme.FlooditTheme
import org.emunix.floodit.viewModel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val gameViewModel by viewModels<GameViewModel>()
            FlooditTheme {
                val systemUiController = rememberSystemUiController()
                val darkTheme = isSystemInDarkTheme()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = if (darkTheme) Color.Black else Color.Transparent,
                        darkIcons = !darkTheme,
                    )
                }

                Surface(color = MaterialTheme.colors.background) {
                    FloodItApp(
                        gameViewModel = gameViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}