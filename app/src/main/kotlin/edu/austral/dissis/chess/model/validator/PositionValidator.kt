package edu.austral.dissis.chess.model.validator

import edu.austral.dissis.chess.enums.ValidatorResultEnum
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.Board
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

class PositionValidator(): Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        if (game.board.squares.contains(movementData.squareTo))
            return ValidatorResultEnum.PASSED
        return ValidatorResultEnum.INVALID_POSITION
    }
}