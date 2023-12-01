package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.common.validator.movement.OnlyDirectionMovement
import edu.austral.dissis.common.validator.game.CaptureValidator
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.validator.ValidatorResultEnum

class PawnMovement : MovementRule {
    private val
            oneStepMovement = FixedStepMovement(1)
    private val twoStepMovement = FixedStepMovement(2)
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val forwardMovement = OnlyDirectionMovement(true)
    private val captureValidator = CaptureValidator()
    override fun validate(movementData: MovementData, game: Game): Boolean {
        return when {
            !forwardMovement.validate(movementData, game) -> false
            captureValidator.validate(movementData, game) == ValidatorResultEnum.PASSED ->
                validateDiagonalAndOneStep(movementData, game)
            movementData.piece?.moveQty!! > 0 ->
                validateOneStepAndStraight(movementData, game)
            else ->
                validateOneOrTwoStepAndStraight(movementData, game)
        }
    }

    private fun validateDiagonalAndOneStep(movementData: MovementData, game: Game): Boolean {
        return diagonalMovement.validate(movementData, game) && oneStepMovement.validate(movementData, game)
    }

    private fun validateOneStepAndStraight(movementData: MovementData, game: Game): Boolean {
        return oneStepMovement.validate(movementData, game) &&
                straightMovement.validate(movementData, game)
    }

    private fun validateOneOrTwoStepAndStraight(movementData: MovementData, game: Game): Boolean {
        return (oneStepMovement.validate(movementData, game) || twoStepMovement.validate(movementData, game)) &&
                straightMovement.validate(movementData, game)
    }

}