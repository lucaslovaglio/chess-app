package edu.austral.dissis.chess.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import kotlin.math.abs

class LMovement : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val diffX: Int = abs(movementData.squareFrom.x - movementData.squareTo.x)
        val diffY: Int = abs(movementData.squareFrom.y - movementData.squareTo.y)
        return diffX == 2 && diffY == 1 || diffX == 1 && diffY == 2
    }
}
