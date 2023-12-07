package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class FirstMovementValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (isFirstMovement(movementData)) {
            return ValidatorResult(ValidatorResultEnum.PASSED)
        }
        return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }

    private fun isFirstMovement(movementData: MovementData): Boolean {
        return movementData.piece?.moveQty!! == 0
    }
}