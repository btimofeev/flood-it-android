package org.emunix.floodit

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import org.emunix.floodit.ui.FloodItApp
import org.emunix.floodit.ui.theme.AppTheme.DARK
import org.emunix.floodit.ui.theme.AppTheme.LIGHT
import org.emunix.floodit.ui.theme.AppTheme.SYSTEM
import org.emunix.floodit.ui.theme.FlooditTheme
import org.emunix.floodit.viewModel.GameViewModel
import org.emunix.floodit.viewModel.ThemeViewModel
import org.emunix.floodit.viewModel.ThemeViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val gameViewModel by viewModels<GameViewModel>()
            val themeViewModel by viewModels<ThemeViewModel> { ThemeViewModelFactory(dataStore) }
            val appTheme = themeViewModel.theme.collectAsState()
            FlooditTheme(darkTheme = when (appTheme.value) {
                LIGHT -> false
                DARK -> true
                SYSTEM -> isSystemInDarkTheme()
            }) {
                Surface(color = MaterialTheme.colors.background) {
                    FloodItApp(
                        gameViewModel = gameViewModel,
                        navController = navController,
                        appTheme = appTheme.value,
                        onAppThemeSettingChanged = { themeViewModel.setTheme(it) }
                    )
                }
            }
        }
    }
}