package org.emunix.floodit.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import org.emunix.floodit.ui.theme.getColorByNumber
import kotlin.math.ceil

@Composable
fun BoardUi(
        modifier: Modifier = Modifier,
        boardState: List<List<Int>>
) {
    BoxWithConstraints(modifier) {
        Canvas(
                modifier = Modifier
                        .width(this.maxWidth)
                        .height(this.maxWidth),
                onDraw = {
                    val rowCount = boardState.count()
                    val cellSize = ceil(size.width / rowCount)
                    for ((y, row) in boardState.withIndex())
                        for ((x, colorNumber) in row.withIndex()) {
                            drawRect(
                                    color = getColorByNumber(colorNumber),
                                    topLeft = Offset(x * cellSize, y * cellSize),
                                    size = Size(cellSize, cellSize)
                            )
                        }
                }
        )
    }
}
