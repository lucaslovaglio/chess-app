package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.movement.LMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule


class KnightMovement : MovementRule {
    private val lMovement = LMovement()
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return lMovement.validate(movementData, game)
    }
}