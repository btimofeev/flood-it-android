package org.emunix.floodit.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.emunix.floodit.ui.FloodItScreen.GAME
import org.emunix.floodit.ui.FloodItScreen.SETTINGS
import org.emunix.floodit.ui.game.GameScreen
import org.emunix.floodit.ui.settings.SettingsScreen
import org.emunix.floodit.ui.theme.AppTheme
import org.emunix.floodit.viewModel.GameViewModel

enum class FloodItScreen {
    GAME,
    SETTINGS,
}

@Composable
fun FloodItApp(
    gameViewModel: GameViewModel,
    navController: NavHostController,
    appTheme: AppTheme,
    onAppThemeSettingChanged: (newTheme: AppTheme) -> Unit
) {
    NavHost(navController = navController, startDestination = GAME.name) {
        composable(route = GAME.name) {
            GameScreen(
                gameState = gameViewModel.gameState,
                turn = gameViewModel.turn,
                maxTurns = gameViewModel.maxTurns,
                boardState = gameViewModel.board,
                onColorButtonClick = { color -> gameViewModel.chooseColor(color) },
                onRestartButtonClick = { gameViewModel.newGame() },
                onSettingsButtonClick = { navController.navigate(SETTINGS.name) }
            )
        }

        composable(route = SETTINGS.name) {
            SettingsScreen(
                appTheme = appTheme,
                onAppThemeSettingChanged = onAppThemeSettingChanged,
                onBackButtonClick = { navController.popBackStack() }
            )
        }
    }
}