package edu.austral.dissis.chess.model

import edu.austral.dissis.chess.enums.ColorEnum
import edu.austral.dissis.chess.enums.PieceEnum
import edu.austral.dissis.chess.interfaces.StartingBoard

class ClassicBoard: StartingBoard {
    val height: Int = 8
    val width: Int = 8
    private val players: List<Player> = listOf(Player(ColorEnum.WHITE), Player(ColorEnum.BLACK))
    override fun generateBoard(): Board {
        val squares = mutableListOf<Square>()

        // Agregar piezas blancas
        for (i in 1..width) {
            squares.add(Square(Piece(i, ColorEnum.WHITE, getPieceTypeForIndex(i), 0), i, 1))
        }

        // Agregar peones blancos
        for (i in 1..width) {
            squares.add(Square(Piece(i + 16, ColorEnum.WHITE, PieceEnum.PAWN, 0), i, 2))
        }

        // Agregar peones negros
        for (i in 1..width) {
            squares.add(Square(Piece(i + 24, ColorEnum.BLACK, PieceEnum.PAWN, 0), i, height-1))
        }

        // Agregar piezas negras
        for (i in 1..width) {
            squares.add(Square(Piece(i + 32, ColorEnum.BLACK, getPieceTypeForIndex(i), 0), i, height))
        }

        // Agregar casilleros vacíos
        addEmptyPieces(squares)



        return Board(width, height, squares)
    }

    override fun getPlayers(): List<Player> {
        return players
    }

    private fun addEmptyPieces(squares: MutableList<Square>) {
        for (col in 3 until height - 1) {
            for (row in 1..width) {
                squares.add(Square(null, row, col))
            }
        }
    }

    // Función para determinar el tipo de pieza según el índice
    private fun getPieceTypeForIndex(index: Int): PieceEnum {
        return when (index) {
            1, 8 -> PieceEnum.ROOK
            2, 7 -> PieceEnum.KNIGHT
            3, 6 -> PieceEnum.BISHOP
            4 -> PieceEnum.QUEEN
            5 -> PieceEnum.KING
            else -> throw IllegalArgumentException("Índice de pieza no válido")
        }
    }

    fun printBoard(board: Board) {
        for (row in 1..height) {
            for (col in 1..width) {
                try {
                    val pieceSymbol = board.getPieceAt(col, row)?.name
                    print("$pieceSymbol\t")
                } catch (e: Exception) {
                    print("-\t")

                }
            }
            println()
        }
    }
}