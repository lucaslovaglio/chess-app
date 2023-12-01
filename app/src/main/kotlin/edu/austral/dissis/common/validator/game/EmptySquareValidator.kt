package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData

class EmptySquareValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        return if (movementData.piece == null) ValidatorResultEnum.INVALID_SQUARE else ValidatorResultEnum.PASSED
    }
}
