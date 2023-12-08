package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.checkers.validator.movement.DiagonalMovementIgnoreObstacles
import edu.austral.dissis.checkers.validator.movement.PieceInMiddleValidator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.game.EmptySquareToValidator
import edu.austral.dissis.common.validator.movement.FixedStepMovement

class CaptureValidator: Validator {
    private val andValidator = AndValidator(
        listOf(
            DiagonalMovementIgnoreObstacles(),
            FixedStepMovement(2),
            PieceInMiddleValidator(),
            EmptySquareToValidator()
        )
    )
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val result = andValidator.validate(movementData, game)
        return if (result.isPassed()) {
            ValidatorResult(ValidatorResultEnum.PASSED, result.getSpecialActions())
        } else {
            ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        }
    }
}