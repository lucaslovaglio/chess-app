package edu.austral.dissis.checkers.validator.piece

import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.game.PromotionValidator
import edu.austral.dissis.common.validator.movement.OnlyDirectionMovement

class PawnMovement: Validator {
    private val normalMove = AndValidator(
        listOf(
            KingMovement(),
            OnlyDirectionMovement(true)
            )
    )

    private val andValidator = AndValidator(
        listOf(
            normalMove,
            PromotionValidator(PieceEnum.PAWN, PieceEnum.KING)
            )
    )
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val result = andValidator.validate(movementData, game)
        return if (result.isPassed()) {
            ValidatorResult(ValidatorResultEnum.PASSED, result.getSpecialActions())
        } else {
            ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        }
    }

}