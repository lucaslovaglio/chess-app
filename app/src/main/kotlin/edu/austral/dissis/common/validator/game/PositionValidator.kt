package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum


class PositionValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (game.board.squares.contains(movementData.squareTo))
            return ValidatorResult(ValidatorResultEnum.PASSED)
        return ValidatorResult(ValidatorResultEnum.INVALID_POSITION)
    }
}