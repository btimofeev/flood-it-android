package org.emunix.floodit.domain

import io.mockk.*
import org.junit.*
import kotlin.random.Random

class GameTest {

    @Test
    fun `test randomize board when game init`() {
        val board = mockk<Board>()
        every { board.height } returns 5
        every { board.width } returns 5
        every { board.get(any(), any()) } returns 1
        every { board.randomize() } just Runs andThenAnswer
                { every { board.get(any(), any()) } answers { Random.nextInt(1,5) } }

        Game(board)
        verify { board.randomize() }
    }

    @Test
    fun `test win condition`() {
        val board = mockk<Board>()
        every { board.height } returns 4
        every { board.width } returns 4
        every { board.colors } returns 3
        every { board.get(any(), any()) } returns 3
        every { board.get(1 ,1) } returns 1

        val game = Game(board)
        assert(game.state == GameState.RUN)

        // set initial board state:
        // 1 1 2 1
        // 2 1 1 1
        // 3 2 3 1
        // 2 1 1 1
        every { board.get(any(), any()) } returns 1
        every { board.get(3 ,1) } returns 2
        every { board.get(1 ,2) } returns 2
        every { board.get(1 ,3) } returns 3
        every { board.get(2 ,3) } returns 2
        every { board.get(3 ,3) } returns 3
        every { board.get(1 ,4) } returns 2

        every { board.set(any(), any(), any()) } just Runs andThenAnswer {
            every { board.get(firstArg(), secondArg()) } returns thirdArg()
        }

        game.chooseColor(1)
        game.chooseColor(2)
        game.chooseColor(3)
        assert(game.state == GameState.WIN)
    }

    @Test
    fun `test lose condition`() {
        val board = mockk<Board>()
        every { board.height } returns 4
        every { board.width } returns 4
        every { board.colors } returns 3
        every { board.get(any(), any()) } returns 2
        every { board.get(4, 4) } returns 3
        every { board.get(1 ,1) } returns 1

        every { board.set(any(), any(), any()) } just Runs andThenAnswer {
            every { board.get(firstArg(), secondArg()) } returns thirdArg()
        }

        val game = Game(board)
        game.maxTurns = 2
        assert(game.state == GameState.RUN)

        game.chooseColor(2)
        game.chooseColor(1)
        assert(game.turn == 2)
        assert(game.state == GameState.LOSE)
    }

    @Test
    fun `test board state`() {
        val board = spyk(Board(BoardConfig(3, 3, 3)))
        every { board.get(any(), any()) } returns 1
        every { board.get(2 ,1) } returns 2
        every { board.get(1 ,2) } returns 2
        every { board.get(3 ,2) } returns 2
        every { board.get(2 ,3) } returns 2
        every { board.get(2 ,2) } returns 3

        val expected = listOf(listOf(1, 2, 1), listOf(2, 3, 2), listOf(1, 2, 1))
        val game = Game(board)
        assert(game.currentBoardState() == expected)
    }

    private fun debugPrintBoard(board: Board) {
        for (y in 1..board.height) {
            for (x in 1..board.width) {
                print("${board.get(x, y)} ")
            }
            print("\n")
        }
        println()
    }
}