package edu.austral.dissis.common.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import kotlin.math.abs

class FixedStepMovement (private val distance: Int) : Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val isValid = abs(movementData.squareFrom.x - movementData.squareTo.x) == distance ||
                abs(movementData.squareFrom.y - movementData.squareTo.y) == distance
        return if (isValid) ValidatorResult(ValidatorResultEnum.PASSED) else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }
}