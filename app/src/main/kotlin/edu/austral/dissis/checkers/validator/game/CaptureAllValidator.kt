package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class CaptureAllValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT) // TODO
    }
}