package edu.austral.dissis.checkers.validator.piece

import edu.austral.dissis.checkers.validator.game.AnyCaptureValidator
import edu.austral.dissis.checkers.validator.game.CaptureValidator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.composite.NotValidator
import edu.austral.dissis.common.validator.composite.OrValidator
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement

class KingMovement: Validator {
    private val normalMove = AndValidator(
        listOf(
            DiagonalMovement(),
            FixedStepMovement(1),
            )
    )

    private val orValidator = OrValidator(
        listOf(
            CaptureValidator(),
            normalMove
            )
    )

    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val result = orValidator.validate(movementData, game)
        return if (result.isPassed()) {
            ValidatorResult(ValidatorResultEnum.PASSED, result.getSpecialActions())
        } else {
            ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        }
    }
}