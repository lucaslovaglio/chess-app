package edu.austral.dissis.chess.model.movements.pieceMovement

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.movements.DiagonalMovement
import edu.austral.dissis.chess.model.movements.HorizontalMovement
import edu.austral.dissis.chess.model.movements.LinearMovement
import edu.austral.dissis.chess.model.movements.StraightMovement


class QueenMovement : MovementRule {
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val horizontalMovement = LinearMovement(true)

    override fun validate(movementData: MovementData, game: Game): Boolean {
        return diagonalMovement.validate(movementData, game) ||
                straightMovement.validate(movementData, game) ||
                horizontalMovement.validate(movementData, game)
    }
}