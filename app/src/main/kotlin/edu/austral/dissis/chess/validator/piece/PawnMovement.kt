package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.game.PromotionValidator
import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.common.validator.movement.OnlyDirectionMovement
import edu.austral.dissis.common.validator.game.CaptureValidator
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.specialAction.SpecialAction
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class PawnMovement : Validator {
    private val oneStepMovement = FixedStepMovement(1)
    private val twoStepMovement = FixedStepMovement(2)
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val forwardMovement = OnlyDirectionMovement(true)
    private val captureValidator = CaptureValidator()
    private val promotionValidator = PromotionValidator()
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val promotionResult = promotionValidator.validate(movementData, game)
        val captureResult = captureValidator.validate(movementData, game)

        val specialActions = mutableListOf<SpecialAction>()
        specialActions.addAll(promotionResult.getSpecialActions())

        if (captureResult.isPassed()) {
            // Agregar las SpecialAction del validador de captura
            specialActions.addAll(captureResult.getSpecialActions())
            // Validar diagonal y paso simple
            if (validateDiagonalAndOneStep(movementData, game)) {
                return ValidatorResult(ValidatorResultEnum.PASSED, specialActions)
            }
        } else if (movementData.piece?.moveQty!! > 0) {
            // Validar paso simple y recto
            if (validateOneStepAndStraight(movementData, game)) {
                return ValidatorResult(ValidatorResultEnum.PASSED, specialActions)
            }
        } else {
            // Validar paso uno o dos y recto
            if (validateOneOrTwoStepAndStraight(movementData, game)) {
                return ValidatorResult(ValidatorResultEnum.PASSED, specialActions)
            }
        }

        return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT, specialActions)
    }

    private fun validateDiagonalAndOneStep(movementData: MovementData, game: Game): Boolean {
        return diagonalMovement.validate(movementData, game).isPassed() && oneStepMovement.validate(movementData, game).isPassed()
    }

    private fun validateOneStepAndStraight(movementData: MovementData, game: Game): Boolean {
        return oneStepMovement.validate(movementData, game).isPassed() &&
                straightMovement.validate(movementData, game).isPassed()
    }

    private fun validateOneOrTwoStepAndStraight(movementData: MovementData, game: Game): Boolean {
        return (oneStepMovement.validate(movementData, game).isPassed() || twoStepMovement.validate(movementData, game).isPassed()) &&
                straightMovement.validate(movementData, game).isPassed()
    }

}