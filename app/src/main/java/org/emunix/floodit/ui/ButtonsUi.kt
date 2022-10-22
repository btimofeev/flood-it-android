package org.emunix.floodit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.emunix.floodit.ui.theme.FlooditTheme
import org.emunix.floodit.ui.theme.getColorByNumber

@Composable
fun ButtonsUi(
    modifier: Modifier = Modifier,
    buttonSize: Dp,
    onButtonClick: (Int) -> Unit,
) {
    Column {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(
                modifier = Modifier.size(buttonSize),
                onClick = { onButtonClick.invoke(1) },
                backgroundColor = getColorByNumber(1)
            ) {}
            FloatingActionButton(
                modifier = Modifier.size(buttonSize),
                onClick = { onButtonClick.invoke(2) },
                backgroundColor = getColorByNumber(2)
            ) {}
            FloatingActionButton(
                modifier = Modifier.size(buttonSize),
                onClick = { onButtonClick.invoke(3) },
                backgroundColor = getColorByNumber(3)
            ) {}
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(
                modifier = Modifier.size(buttonSize),
                onClick = { onButtonClick.invoke(4) },
                backgroundColor = getColorByNumber(4)
            ) {}
            FloatingActionButton(
                modifier = Modifier.size(buttonSize),
                onClick = { onButtonClick.invoke(5) },
                backgroundColor = getColorByNumber(5)
            ) {}
            FloatingActionButton(
                modifier = Modifier.size(buttonSize),
                onClick = { onButtonClick.invoke(6) },
                backgroundColor = getColorByNumber(6)
            ) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsUiPreview() {
    FlooditTheme {
        ButtonsUi(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp),
            buttonSize = 64.dp
        ) { }
    }
}