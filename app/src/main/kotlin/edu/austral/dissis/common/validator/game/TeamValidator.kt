package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum


class TeamValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (game.getCurrentPlayer().color != movementData.piece?.color)
            return ValidatorResult(ValidatorResultEnum.INVALID_TEAM)
        return ValidatorResult(ValidatorResultEnum.PASSED)
    }
}