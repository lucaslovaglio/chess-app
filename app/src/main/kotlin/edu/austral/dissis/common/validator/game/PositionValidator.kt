package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResultEnum


class PositionValidator(): Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        if (game.board.squares.contains(movementData.squareTo))
            return ValidatorResultEnum.PASSED
        return ValidatorResultEnum.INVALID_POSITION
    }
}