package edu.austral.dissis.chess.model.movements.pieceMovement

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.movements.DiagonalMovement

class BishopMovement : MovementRule {
    private val diagonalMovement = DiagonalMovement()

    override fun validate(movementData: MovementData, game: Game): Boolean {
        return diagonalMovement.validate(movementData, game)
    }
}