package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.validator.game.CastlingValidator
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.specialAction.SpecialAction
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.composite.OrValidator

class KingMovement : Validator {
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val horizontalMovement = LinearMovement(true)
    private val oneStepMovement = FixedStepMovement(1)
    private val castlingValidator = CastlingValidator()

    private val directionValidator = OrValidator(
        listOf(
            diagonalMovement,
            straightMovement,
            horizontalMovement
        )
    )

    private val normalMove = AndValidator(
        listOf(
            directionValidator,
            oneStepMovement
        )
    )

    private val orValidator = OrValidator(
        listOf(
            castlingValidator,
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


//        val castlingResult = castlingValidator.validate(movementData, game)
//        val specialActions = mutableListOf<SpecialAction>()
//        specialActions.addAll(castlingResult.getSpecialActions())
//
//        val isValid = (diagonalMovement.validate(movementData, game).isPassed() ||
//                straightMovement.validate(movementData, game).isPassed() ||
//                horizontalMovement.validate(movementData, game).isPassed()) &&
//                oneStepMovement.validate(movementData, game).isPassed()
//
//        return if (isValid || castlingResult.isPassed()) ValidatorResult(ValidatorResultEnum.PASSED, specialActions)
//        else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }
}