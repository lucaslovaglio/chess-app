package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.composite.NotValidator
import edu.austral.dissis.common.validator.composite.OrValidator

class CaptureIfPossibleValidator: Validator {
    private val mustCapture = OrValidator(
        listOf(
            AndValidator(
                listOf(
                    AnyCaptureValidator(),
                    CaptureValidator()
                )
            ),
            NotValidator(AnyCaptureValidator())
        )
    )
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val result = mustCapture.validate(movementData, game)
        return if (result.isPassed()) {
            ValidatorResult(ValidatorResultEnum.PASSED, result.getSpecialActions())
        } else {
            ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        }
    }
}