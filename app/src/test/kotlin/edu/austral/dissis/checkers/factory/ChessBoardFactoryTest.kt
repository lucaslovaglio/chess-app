package edu.austral.dissis.checkers.factory


import org.junit.jupiter.api.Test


class ChessBoardFactoryTest {

    @Test
    fun generateBoard() {
        val startingBoard = ClassicBoard
        val board = startingBoard.generateBoard()
//        assertThat(board.squares.size, 64)
//        assert(result.size == 32)
        ClassicBoard.printBoard(board)
        val i = 1
    }
}