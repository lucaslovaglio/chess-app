package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResultEnum


class TeamValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        if (game.getCurrentPlayer().color != movementData.piece?.color)
            return ValidatorResultEnum.INVALID_TEAM
        return ValidatorResultEnum.PASSED
    }
}