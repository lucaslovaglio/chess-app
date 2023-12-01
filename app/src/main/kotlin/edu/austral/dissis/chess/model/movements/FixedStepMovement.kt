package edu.austral.dissis.chess.model.movements

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import kotlin.math.abs

class FixedStepMovement (val distance: Int) : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return abs(movementData.squareFrom.x - movementData.squareTo.x) == distance ||
                abs(movementData.squareFrom.y - movementData.squareTo.y) == distance
    }
}