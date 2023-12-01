package edu.austral.dissis.common.movement

import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.piece.Piece

data class MovementData(
    val piece: Piece?,
    val squareFrom: Square,
    val squareTo: Square,
)