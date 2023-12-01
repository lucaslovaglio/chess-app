package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData

class CaptureValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        if (!movementData.squareTo.isEmpty() && movementData.squareTo.piece?.color != movementData.piece?.color) {
            return ValidatorResultEnum.PASSED
        }
        return ValidatorResultEnum.INVALID_CAPTURE
    }
}