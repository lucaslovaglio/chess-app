package edu.austral.dissis.chess.model.movements.pieceMovement

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.movements.*

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