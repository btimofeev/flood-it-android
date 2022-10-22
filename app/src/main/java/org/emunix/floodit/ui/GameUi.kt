package org.emunix.floodit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.emunix.floodit.R
import org.emunix.floodit.domain.GameState

@Composable
fun GameUi(
    gameState: GameState,
    turn: String,
    maxTurns: String,
    boardState: List<List<Int>>,
    onColorButtonClick: (Int) -> Unit,
    onRestartButtonClick: () -> Unit,
) {
    Scaffold(
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
                    //IconButton(onClick = { shouldShowNewGameDialog = true }) { Icon(Icons.Filled.Add) }
                },
                elevation = 0.dp
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
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
                    .padding(24.dp, 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(40)
                    ),
                boardState = boardState
            )

            when (gameState) {
                GameState.WIN -> GameOverUi(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    message = stringResource(R.string.game_over_win),
                    onRestartClick = onRestartButtonClick
                )
                GameState.LOSE -> GameOverUi(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    message = stringResource(R.string.game_over_lose),
                    onRestartClick = onRestartButtonClick
                )
                else -> ButtonsUi(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 16.dp),
                    buttonSize = 64.dp,
                    onButtonClick = onColorButtonClick
                )
            }
        }
    }
}
