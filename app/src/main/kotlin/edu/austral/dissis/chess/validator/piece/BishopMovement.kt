package edu.austral.dissis.chess.validator.piece


import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.movement.DiagonalMovement

class BishopMovement : Validator {
    private val diagonalMovement = DiagonalMovement()

    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        return diagonalMovement.validate(movementData, game)
    }
}