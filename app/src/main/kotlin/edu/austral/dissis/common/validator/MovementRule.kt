package edu.austral.dissis.common.validator

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData

interface MovementRule {
    fun validate(movementData: MovementData, game: Game): Boolean
}