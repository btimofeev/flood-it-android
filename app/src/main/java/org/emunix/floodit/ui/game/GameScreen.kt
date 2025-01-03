package org.emunix.floodit.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.emunix.floodit.R
import org.emunix.floodit.R.string
import org.emunix.floodit.domain.GameState
import org.emunix.floodit.domain.GameState.RUN
import org.emunix.floodit.ui.theme.FlooditTheme

@Composable
fun GameScreen(
    gameState: GameState,
    turn: String,
    maxTurns: String,
    boardState: List<List<Int>>,
    onColorButtonClick: (Int) -> Unit,
    onNewGameButtonClick: () -> Unit,
    onTryAgainButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.app_name)
                    )
                },
                contentColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                actions = {
                    IconButton(onClick = { onSettingsButtonClick.invoke() }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(id = string.settings)
                        )
                    }
                },
                elevation = 0.dp
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(padding),
            verticalArrangement = Arrangement.Top
        ) {
            TurnsUi(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                turn = turn,
                maxTurns = maxTurns
            )

            BoardUi(
                modifier = Modifier
                    .weight(weight = 0.6f, fill = true)
                    .padding(24.dp, 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(40)
                    ),
                boardState = boardState
            )

            BoxWithConstraints(
                modifier = Modifier
                    .weight(weight = 0.4f, fill = true),
                contentAlignment = Alignment.Center,
            ) {
                when (gameState) {
                    GameState.WIN -> GameOverUi(
                        modifier = Modifier.fillMaxWidth(),
                        message = stringResource(R.string.game_over_win),
                        onNewGameClick = onNewGameButtonClick,
                        onTryAgainButtonClick = {},
                        isTryAgainVisible = false,
                    )
                    GameState.LOSE -> GameOverUi(
                        modifier = Modifier.fillMaxWidth(),
                        message = stringResource(R.string.game_over_lose),
                        onNewGameClick = onNewGameButtonClick,
                        onTryAgainButtonClick = onTryAgainButtonClick,
                        isTryAgainVisible = true,
                    )
                    else -> ButtonsUi(
                        modifier = Modifier
                            .padding(16.dp, 0.dp)
                            .fillMaxWidth(),
                        onButtonClick = onColorButtonClick
                    )
                }
            }
        }
    }
}

@Preview(
    heightDp = 500,
    widthDp = 400,
    showBackground = true
)
@Composable
fun GameUiPreview() {
    FlooditTheme {
        GameScreen(
            gameState = RUN,
            turn = "13",
            maxTurns = "24",
            boardState = listOf(
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
            ),
            onColorButtonClick = {},
            onNewGameButtonClick = {},
            onTryAgainButtonClick = {},
            onSettingsButtonClick = {},
        )
    }
}