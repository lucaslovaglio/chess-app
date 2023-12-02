package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.factory.StartingBoard
import edu.austral.dissis.common.game.Player
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece

class ClassicBoard: StartingBoard {
    private val height: Int = 8
    private val width: Int = 8
    private val players: List<Player> = listOf(Player(ColorEnum.WHITE), Player(ColorEnum.BLACK))

    override fun generateBoard(): Board {
        val squares = mutableListOf<Square>()
        var pieceIdCounter = 1

        // Agregar fichas blancas
        for (row in 1..3) {
            for (col in 1..width step 2) {
                val piece = Piece(pieceIdCounter++, ColorEnum.WHITE, PieceEnum.PAWN, 0)
                squares.add(Square(piece, col + (row % 2), row))
            }
        }

        // Agregar fichas negras
        for (row in height - 2 until height + 1) {
            for (col in 1..width step 2) {
                val piece = Piece(pieceIdCounter++, ColorEnum.BLACK, PieceEnum.PAWN, 0)
                squares.add(Square(piece, col + (row % 2), row))
            }
        }

        // Agregar casilleros vacíos
        addEmptySquares(squares)

        return Board(width, height, squares)
    }

    override fun getPlayers(): List<Player> {
        return players
    }

    private fun addEmptySquares(squares: MutableList<Square>) {
//        var currentId = startId

        for (row in 1..height) {
            for (col in 1..width) {
                if (squares.none { it.x == col && it.y == row }) {
//                    val piece = Piece(currentId++, ColorEnum.WHITE, PieceEnum.KING, 0)
                    squares.add(Square(null, col, row))
                }
            }
        }
    }




    fun printBoard(board: Board) {
        for (row in 1..height) {
            for (col in 1..width) {
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