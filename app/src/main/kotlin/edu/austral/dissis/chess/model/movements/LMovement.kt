package edu.austral.dissis.chess.model.movements

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import kotlin.math.abs

class LMovement : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val diffX: Int = abs(movementData.squareFrom.x - movementData.squareTo.x)
        val diffY: Int = abs(movementData.squareFrom.y - movementData.squareTo.y)
        return diffX == 2 && diffY == 1 || diffX == 1 && diffY == 2
    }
}
