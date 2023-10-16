package org.emunix.floodit.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.emunix.floodit.R.string
import org.emunix.floodit.ui.theme.AppTheme
import org.emunix.floodit.ui.theme.AppTheme.DARK
import org.emunix.floodit.ui.theme.AppTheme.LIGHT
import org.emunix.floodit.ui.theme.AppTheme.SYSTEM

@Composable
fun ThemeSettings(
    selected: AppTheme,
    onSelectTheme: (AppTheme) -> Unit,
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = string.theme),
        style = MaterialTheme.typography.h6,
    )
    Column(Modifier.selectableGroup())
    {
        listOf(
            SYSTEM, LIGHT, DARK
        ).forEach { theme ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectTheme(theme) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == theme,
                    onClick = { onSelectTheme(theme) }
                )
                Text(
                    text = stringResource(id = theme.nameRes)
                )
            }
        }
    }
}