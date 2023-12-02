package edu.austral.dissis.checkers.validator.piece

import edu.austral.dissis.checkers.validator.movement.CaptureForwardMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.game.CaptureValidator
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.common.validator.movement.OnlyDirectionMovement
import kotlin.math.abs

class PawnMovement: MovementRule {
    private val diagonalMovement = DiagonalMovement()
    private val oneStepMovement = FixedStepMovement(1)
    private val forwardMovement = OnlyDirectionMovement(true)
    private val captureValidator = CaptureValidator()
    private val threeStepMovement = FixedStepMovement(2)
    private val captureForwardMovement = CaptureForwardMovement()



    override fun validate(movementData: MovementData, game: Game): Boolean {
        return when {
            !forwardMovement.validate(movementData, game) -> false
            possibleCapture(movementData, game) ->
                tryToCapture(movementData, game)

//                validateDiagonalAndOneStep(movementData, game)
//            movementData.piece?.moveQty!! > 0 ->
//                validateOneStepAndStraight(movementData, game)
            else ->
                diagonalMovement.validate(movementData, game) && oneStepMovement.validate(movementData, game)
        }
    }

    private fun possibleCapture(movementData: MovementData, game: Game): Boolean {
        return abs(movementData.squareFrom.x - movementData.squareTo.x) == abs(movementData.squareFrom.y - movementData.squareTo.y)
                && threeStepMovement.validate(movementData, game)
    }

    private fun tryToCapture(movementData: MovementData, game: Game): Boolean {
//        val game
        val result = captureForwardMovement.validate(movementData, game)
        return result
    }

}