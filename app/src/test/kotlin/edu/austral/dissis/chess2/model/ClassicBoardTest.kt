package edu.austral.dissis.chess2.model

import edu.austral.dissis.chess.chess.factory.ClassicBoard

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