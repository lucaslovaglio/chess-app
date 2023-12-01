package edu.austral.dissis.chess.model.validator

import edu.austral.dissis.chess.enums.ValidatorResultEnum
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

class CaptureValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        if (!movementData.squareTo.isEmpty() && movementData.squareTo.piece?.color != movementData.piece?.color) {
            return ValidatorResultEnum.PASSED
        }
        return ValidatorResultEnum.INVALID_CAPTURE
    }
}