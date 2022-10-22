package org.emunix.floodit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import org.emunix.floodit.ui.GameUi
import org.emunix.floodit.ui.theme.FlooditTheme
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.emunix.floodit.viewModel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameViewModel by viewModels<GameViewModel>()

        setContent {
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
                    GameUi(
                        gameState = gameViewModel.gameState,
                        turn = gameViewModel.turn,
                        maxTurns = gameViewModel.maxTurns,
                        boardState = gameViewModel.board,
                        onColorButtonClick = { color -> gameViewModel.chooseColor(color) },
                        onRestartButtonClick = { gameViewModel.newGame() }
                    )
                }
            }
        }
    }
}