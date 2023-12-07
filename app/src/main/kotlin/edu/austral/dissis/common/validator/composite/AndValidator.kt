package edu.austral.dissis.common.validator.composite

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.specialAction.SpecialAction
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class AndValidator(private val validators: List<Validator>) : Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val specialActions = mutableListOf<SpecialAction>()

        for (validator in validators) {
            val result = validator.validate(movementData, game)
            if (!result.isPassed()) {
                return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT, specialActions, result.getResultMessage())
            }
            specialActions.addAll(result.getSpecialActions())
        }

        return ValidatorResult(ValidatorResultEnum.PASSED, specialActions)
    }
}
