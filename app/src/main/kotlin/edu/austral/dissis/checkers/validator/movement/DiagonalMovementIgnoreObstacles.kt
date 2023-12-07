package edu.austral.dissis.checkers.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import kotlin.math.abs

class DiagonalMovementIgnoreObstacles: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (isDiagonal(movementData)) {
            return ValidatorResult(ValidatorResultEnum.PASSED)
        }
        return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }

    private fun isDiagonal(movementData: MovementData): Boolean {
        val from = movementData.squareFrom
        val to = movementData.squareTo
        return abs(from.x - to.x) == abs(from.y - to.y)
    }
}
