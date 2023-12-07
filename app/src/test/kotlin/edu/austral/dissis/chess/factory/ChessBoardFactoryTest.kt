package edu.austral.dissis.chess.factory


import org.junit.jupiter.api.Test


class ChessBoardFactoryTest {

    @Test
    fun generateBoard() {
        val startingBoard = CapablancaBoard
        val board = startingBoard.generateBoard()
//        assertThat(board.squares.size, 64)
//        assert(result.size == 32)
        CapablancaBoard.printBoard(board)
        val i = 1
    }
}