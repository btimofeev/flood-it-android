package org.emunix.floodit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.emunix.floodit.R
import org.emunix.floodit.ui.theme.FlooditTheme
import java.util.*

@Composable
fun GameOverUi(
        modifier: Modifier = Modifier,
        message: String,
        onRestartClick: () -> Unit
){
    Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Text(
                modifier = Modifier

                        .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                text = message
        )

        Button(
                onClick = { onRestartClick.invoke() },
                modifier = Modifier
                        .padding(16.dp)
        ) {
            Text(stringResource(R.string.game_over_restart).uppercase(Locale.ROOT))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameOverUiPreview() {
    FlooditTheme {
        GameOverUi(
                modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                message = stringResource(id = R.string.game_over_win)
        ) { }
    }
}
