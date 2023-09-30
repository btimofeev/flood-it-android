package org.emunix.floodit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.emunix.floodit.R
import org.emunix.floodit.ui.theme.FlooditTheme
import org.emunix.floodit.ui.theme.Red
import java.util.*

@Composable
fun GameOverUi(
    modifier: Modifier = Modifier,
    message: String,
    onRestartClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            text = message
        )

        Button(
            onClick = { onRestartClick.invoke() },
            modifier = Modifier
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Red,
                contentColor = Color.White,
            )
        ) {
            Text(stringResource(R.string.game_over_restart).uppercase(Locale.ROOT))
        }
    }
}

@Preview(
    heightDp = 200,
    widthDp = 400,
    showBackground = true
)
@Composable
fun GameOverUiPreview() {
    FlooditTheme {
        GameOverUi(
            modifier = Modifier
                .fillMaxWidth(),
            message = stringResource(id = R.string.game_over_win)
        ) { }
    }
}
