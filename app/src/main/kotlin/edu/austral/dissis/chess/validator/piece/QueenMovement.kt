package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.movement.DiagonalMovement


class QueenMovement : Validator {
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val horizontalMovement = LinearMovement(true)

    override fun validate(movementData: MovementData, game: Game): ValidatorResult {

        val isValid =  diagonalMovement.validate(movementData, game).isPassed() ||
                straightMovement.validate(movementData, game).isPassed() ||
                horizontalMovement.validate(movementData, game).isPassed()

        return if (isValid) ValidatorResult(ValidatorResultEnum.PASSED)
        else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }
}