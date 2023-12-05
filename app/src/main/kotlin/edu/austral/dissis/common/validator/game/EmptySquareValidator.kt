package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.ValidatorResult

class EmptySquareValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        return if (movementData.piece == null)
                    ValidatorResult(ValidatorResultEnum.INVALID_SQUARE)
                else ValidatorResult(ValidatorResultEnum.PASSED)
    }
}
