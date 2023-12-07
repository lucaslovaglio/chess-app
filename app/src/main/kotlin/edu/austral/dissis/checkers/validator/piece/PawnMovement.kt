package edu.austral.dissis.checkers.validator.piece

import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.checkers.validator.movement.PieceInMiddleValidator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.chess.validator.game.CaptureValidator
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.composite.OrValidator
import edu.austral.dissis.common.validator.game.PromotionValidator
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.common.validator.movement.OnlyDirectionMovement
import kotlin.math.abs

class PawnMovement: Validator {
    private val diagonalMovement = DiagonalMovement()
    private val oneStepMovement = FixedStepMovement(1)
    private val forwardMovement = OnlyDirectionMovement(true)
    private val captureValidator = CaptureValidator()
    private val threeStepMovement = FixedStepMovement(2)
    private val pieceInMiddleValidator = PieceInMiddleValidator()

    private val normalMove = AndValidator(
        listOf(
            KingMovement(),
            OnlyDirectionMovement(true)
            )
    )

    private val andValidator = AndValidator(
        listOf(
            normalMove,
            PromotionValidator(PieceEnum.PAWN, PieceEnum.KING)
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


//    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
//        return when {
//            !forwardMovement.validate(movementData, game).isPassed() -> ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
//            possibleCapture(movementData, game) ->
//                tryToCapture(movementData, game)
//
////                validateDiagonalAndOneStep(movementData, game)
////            movementData.piece?.moveQty!! > 0 ->
////                validateOneStepAndStraight(movementData, game)
//            else ->
//                if (validateDiagonalAndOneStep(movementData, game).isPassed() )
//                    ValidatorResult(ValidatorResultEnum.PASSED)
//                else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
//
//        }
//    }
//
//    private fun possibleCapture(movementData: MovementData, game: Game): Boolean {
//        return abs(movementData.squareFrom.x - movementData.squareTo.x) == abs(movementData.squareFrom.y - movementData.squareTo.y)
//                && threeStepMovement.validate(movementData, game).isPassed()
//    }
//
//    private fun tryToCapture(movementData: MovementData, game: Game): ValidatorResult {
////        val game
//        val result = pieceInMiddleValidator.validate(movementData, game)
//        return result
//    }
//
//    private fun validateDiagonalAndOneStep(movementData: MovementData, game: Game): ValidatorResult {
//        val diagonalResult = diagonalMovement.validate(movementData, game)
//        val oneStepResult = oneStepMovement.validate(movementData, game)
//        return if (diagonalResult.isPassed() && oneStepResult.isPassed() && movementData.squareTo.isEmpty()) {
//            ValidatorResult(ValidatorResultEnum.PASSED)
//        } else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
//    }

}