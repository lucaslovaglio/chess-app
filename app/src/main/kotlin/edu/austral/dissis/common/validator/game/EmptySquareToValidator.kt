package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class EmptySquareToValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        return if (movementData.squareTo.piece != null)
                    ValidatorResult(ValidatorResultEnum.INVALID_SQUARE_TO)
                else ValidatorResult(ValidatorResultEnum.PASSED)
    }
}