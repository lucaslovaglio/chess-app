package edu.austral.dissis.chess.model.validator

import edu.austral.dissis.chess.enums.ValidatorResultEnum
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

class TeamValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        if (game.getCurrentPlayer().color != movementData.piece?.color)
            return ValidatorResultEnum.INVALID_TEAM
        return ValidatorResultEnum.PASSED
    }
}