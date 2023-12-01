package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule

class KingMovement : MovementRule {
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val horizontalMovement = LinearMovement(true)
    private val oneStepMovement = FixedStepMovement(1)

    override fun validate(movementData: MovementData, game: Game): Boolean {
        return (diagonalMovement.validate(movementData, game) ||
                straightMovement.validate(movementData, game) ||
                horizontalMovement.validate(movementData, game)) &&
                oneStepMovement.validate(movementData, game)
    }
}