package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.OrValidator

class ChancellorMovement: Validator {
    private val orValidator = OrValidator(
        listOf(
            RookMovement(),
            KnightMovement()
            )
    )
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val result = orValidator.validate(movementData, game)
        return if (result.isPassed()) {
            ValidatorResult(ValidatorResultEnum.PASSED, result.getSpecialActions())
        } else {
            ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        }
    }
}