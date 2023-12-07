package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.movement.LMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult


class KnightMovement : Validator {
    private val lMovement = LMovement()
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        return lMovement.validate(movementData, game)
    }
}