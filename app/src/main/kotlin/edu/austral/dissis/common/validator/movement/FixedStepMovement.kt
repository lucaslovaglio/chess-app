package edu.austral.dissis.common.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import kotlin.math.abs

class FixedStepMovement (val distance: Int) : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return abs(movementData.squareFrom.x - movementData.squareTo.x) == distance ||
                abs(movementData.squareFrom.y - movementData.squareTo.y) == distance
    }
}