package edu.austral.dissis.chess.interfaces

import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

interface MovementRule {
    fun validate(movementData: MovementData, game: Game): Boolean
}