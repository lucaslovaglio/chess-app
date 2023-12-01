package edu.austral.dissis.chess.model.validator

import edu.austral.dissis.chess.enums.ValidatorResultEnum
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

class EmptySquareValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        return if (movementData.piece == null) ValidatorResultEnum.INVALID_SQUARE else ValidatorResultEnum.PASSED
    }
}
