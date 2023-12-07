package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.factory.StartingBoard
import edu.austral.dissis.common.game.Player
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece

object ClassicBoard: StartingBoard {
    private const val HEIGHT: Int = 8
    private const val WIDTH: Int = 8
    private val players: List<Player> = listOf(Player(ColorEnum.WHITE), Player(ColorEnum.BLACK))

    override fun generateBoard(): Board {
        val squares = mutableListOf<Square>()
        var pieceIdCounter = 1

        // Agregar fichas blancas
        for (row in 1..3) {
            for (col in 1..WIDTH step 2) {
                val piece = Piece(pieceIdCounter++, ColorEnum.WHITE, PieceEnum.PAWN, 0)
                squares.add(Square(piece, col + (row % 2), row))
            }
        }

        // Agregar fichas negras
        for (row in HEIGHT - 2 until HEIGHT + 1) {
            for (col in 1..WIDTH step 2) {
                val piece = Piece(pieceIdCounter++, ColorEnum.BLACK, PieceEnum.PAWN, 0)
                squares.add(Square(piece, col + (row % 2), row))
            }
        }

        // Agregar casilleros vacíos
        addEmptySquares(squares)

        return Board(WIDTH, HEIGHT, squares)
    }

    override fun getPlayers(): List<Player> {
        return players
    }

    private fun addEmptySquares(squares: MutableList<Square>) {
//        var currentId = startId

        for (row in 1..HEIGHT) {
            for (col in 1..WIDTH) {
                if (squares.none { it.x == col && it.y == row }) {
//                    val piece = Piece(currentId++, ColorEnum.WHITE, PieceEnum.KING, 0)
                    squares.add(Square(null, col, row))
                }
            }
        }
    }




    fun printBoard(board: Board) {
        for (row in 1..HEIGHT) {
            for (col in 1..WIDTH) {
                try {
                    val pieceSymbol = board.getPieceAt(col, row)?.name ?: "-"
                    print("$pieceSymbol\t")
                } catch (e: Exception) {
                    print("-\t")
                }
            }
            println()
        }
    }
}