package edu.austral.dissis.common.board

import edu.austral.dissis.common.piece.Piece

class Square(
    val piece: Piece?,
    val x: Int,
    val y: Int
) {
    fun update(piece: Piece?): Square {
        return Square(piece, x, y)
    }

    fun isEmpty(): Boolean {
        return piece == null
    }
}