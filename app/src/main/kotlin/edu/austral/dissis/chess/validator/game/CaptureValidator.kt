package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.ValidatorResult

class CaptureValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (!movementData.squareTo.isEmpty() && movementData.squareTo.piece?.color != movementData.piece?.color) {
            return ValidatorResult(ValidatorResultEnum.PASSED)
        }
        return ValidatorResult(ValidatorResultEnum.INVALID_CAPTURE)
    }
}