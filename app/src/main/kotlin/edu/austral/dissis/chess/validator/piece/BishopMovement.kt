package edu.austral.dissis.chess.validator.piece


import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.movement.DiagonalMovement

class BishopMovement : MovementRule {
    private val diagonalMovement = DiagonalMovement()

    override fun validate(movementData: MovementData, game: Game): Boolean {
        return diagonalMovement.validate(movementData, game)
    }
}