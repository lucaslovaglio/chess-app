package edu.austral.dissis.common.board

import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece

class Board(
    val width: Int,
    val height: Int,
    val squares: List<Square>,
) {
    fun move(movementData: MovementData): Board {
        val piece = movementData.piece!!
        return Board(width, height, squares.map {
            when (it) {
                movementData.squareFrom -> {
                    Square(null, it.x, it.y)
                }
                movementData.squareTo -> {
                    Square(piece.copy(moveQty = piece.moveQty + 1), it.x, it.y)
                }
                else -> {
                    it
                }
            }
        })
    }

    fun getPieceAt(x: Int, y: Int): Piece? {
        return getSquareAt(x, y).piece
    }

    fun getSquareAt(x: Int, y: Int): Square {
        return squares.find { it.x == x && it.y == y }!!
    }

    fun searchPiece(id: Int): Square? {
        return squares.find { it.piece?.id == id }
    }

    fun getEmptySquares(): List<Square> {
        return squares.filter { it.isEmpty() }
    }

    fun getPieces(): List<Piece> {
        return squares.filter { !it.isEmpty() }.map { it.piece!! }
    }

    fun getNonEmptySquares(): List<Square> {
        return squares.filter { !it.isEmpty() }
    }
}