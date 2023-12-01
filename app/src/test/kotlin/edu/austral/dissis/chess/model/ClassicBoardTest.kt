package edu.austral.dissis.chess.model

import edu.austral.dissis.chess.enums.ColorEnum
import edu.austral.dissis.chess.enums.PieceEnum
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test


class ClassicBoardTest {

    @Test
    fun generateBoard() {
        val startingBoard = ClassicBoard()
        val board = startingBoard.generateBoard()
//        assertThat(board.squares.size, 64)
//        assert(result.size == 32)
        ClassicBoard().printBoard(board)
        val i = 1
    }
}