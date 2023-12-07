package edu.austral.dissis.checkers.validator.movement

import edu.austral.dissis.checkers.movement.specialAction.CaptureMiddleAction
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class PieceInMiddleValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val board = game.board
        val piece = movementData.piece
        val from = movementData.squareFrom
        val to = movementData.squareTo
        val middle = Pair((from.x + to.x) / 2, (from.y + to.y) / 2)
        val squareMiddle = board.getSquareAt(middle.first, middle.second)
        val pieceMiddle: Piece? = squareMiddle.piece
        val specialAction = CaptureMiddleAction(squareMiddle)
        return if (pieceMiddle != null && pieceMiddle.color != piece?.color) {
            ValidatorResult(ValidatorResultEnum.PASSED, listOf(specialAction))
        } else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }
}