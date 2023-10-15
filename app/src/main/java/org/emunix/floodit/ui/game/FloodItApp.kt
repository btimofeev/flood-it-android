package org.emunix.floodit.ui.game

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.emunix.floodit.viewModel.GameViewModel

enum class FloodItScreen {
    GAME,
}

@Composable
fun FloodItApp(
    gameViewModel: GameViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = FloodItScreen.GAME.name) {
        composable(route = FloodItScreen.GAME.name) {
            GameScreen(
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