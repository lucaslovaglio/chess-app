package edu.austral.dissis.chess.model.movements.pieceMovement

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.movements.LMovement

class KnightMovement : MovementRule {
    private val lMovement = LMovement()
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return lMovement.validate(movementData, game)
    }
}