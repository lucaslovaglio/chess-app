package edu.austral.dissis.chess.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import kotlin.math.abs

class LMovement : Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val diffX: Int = abs(movementData.squareFrom.x - movementData.squareTo.x)
        val diffY: Int = abs(movementData.squareFrom.y - movementData.squareTo.y)
        return if (diffX == 2 && diffY == 1 || diffX == 1 && diffY == 2)
                    ValidatorResult(ValidatorResultEnum.PASSED)
                else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }
}
