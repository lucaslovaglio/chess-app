package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.specialAction.PromotionAction
import edu.austral.dissis.common.movement.specialAction.SpecialAction
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class PromotionValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val piece = movementData.piece!!
        val board = game.board
        val limit = if (piece.color == ColorEnum.WHITE) board.height else 1

        if (movementData.squareTo.y == limit && piece.name == PieceEnum.PAWN) {
            val specialAction: SpecialAction = PromotionAction(movementData)
            return ValidatorResult(ValidatorResultEnum.PASSED, listOf(specialAction))
        }
        return ValidatorResult(ValidatorResultEnum.PASSED) // Devuelve passed porque el movimiento es valido pero no se ejecuta la coronacion
    }
}