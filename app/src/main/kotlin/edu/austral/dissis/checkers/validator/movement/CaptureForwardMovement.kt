package edu.austral.dissis.checkers.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.MovementRule

class CaptureForwardMovement: MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val board = game.board
        val piece = movementData.piece
        val xFrom = movementData.squareFrom.x
        val yFrom = movementData.squareFrom.y
        val xTo = movementData.squareTo.x
        val yTo = movementData.squareTo.y
        val xMiddle = (xFrom + xTo) / 2
        val yMiddle = (yFrom + yTo) / 2
        val squareMiddle = board.getSquareAt(xMiddle, yMiddle)
        val pieceMiddle: Piece? = squareMiddle.piece
        return pieceMiddle != null && pieceMiddle.color != piece?.color
    }
}