package edu.austral.dissis.chess.interfaces

import edu.austral.dissis.chess.enums.ValidatorResultEnum
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

interface Validator {
    fun validate(movementData: MovementData, game: Game): ValidatorResultEnum
}