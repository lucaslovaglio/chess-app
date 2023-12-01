package edu.austral.dissis.chess.model.movements.pieceMovement

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.movements.HorizontalMovement
import edu.austral.dissis.chess.model.movements.LinearMovement
import edu.austral.dissis.chess.model.movements.StraightMovement

class RookMovement : MovementRule {
    private val horizontalMovement = LinearMovement(true)
    private val straightMovement = LinearMovement(false)
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return horizontalMovement.validate(movementData, game) || straightMovement.validate(movementData, game)
    }
}