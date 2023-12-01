package edu.austral.dissis.chess.model

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