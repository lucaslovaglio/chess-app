package edu.austral.dissis.chess.model

data class MovementData(
    val piece: Piece?,
    val squareFrom: Square,
    val squareTo: Square,
)