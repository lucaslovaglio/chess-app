package edu.austral.dissis.common.validator.composite

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.specialAction.SpecialAction
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class NotValidator(private val validator: Validator) : Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val specialActions = mutableListOf<SpecialAction>()

        val result = validator.validate(movementData, game)

        return if (!result.isPassed()) {
            ValidatorResult(ValidatorResultEnum.PASSED, specialActions)
        } else {
            ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT, specialActions)
        }
    }
}
