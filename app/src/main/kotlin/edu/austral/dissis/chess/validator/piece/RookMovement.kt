package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule

class RookMovement : MovementRule {
    private val horizontalMovement = LinearMovement(true)
    private val straightMovement = LinearMovement(false)
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return horizontalMovement.validate(movementData, game) || straightMovement.validate(movementData, game)
    }
}