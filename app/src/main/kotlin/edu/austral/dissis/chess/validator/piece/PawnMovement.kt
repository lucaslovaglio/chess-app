package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.validator.game.PromotionValidator
import edu.austral.dissis.chess.validator.movement.LinearMovement
import edu.austral.dissis.common.validator.movement.DiagonalMovement
import edu.austral.dissis.common.validator.movement.FixedStepMovement
import edu.austral.dissis.common.validator.movement.OnlyDirectionMovement
import edu.austral.dissis.chess.validator.game.CaptureValidator
import edu.austral.dissis.common.validator.game.EmptySquareToValidator
import edu.austral.dissis.chess.validator.game.FirstMovementValidator
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.composite.NotValidator
import edu.austral.dissis.common.validator.composite.OrValidator

class PawnMovement : Validator {
    private val oneStepMovement = FixedStepMovement(1)
    private val twoStepMovement = FixedStepMovement(2)
    private val diagonalMovement = DiagonalMovement()
    private val straightMovement = LinearMovement(false)
    private val forwardMovement = OnlyDirectionMovement(true)
    private val captureValidator = CaptureValidator()
    private val promotionValidator = PromotionValidator(PieceEnum.PAWN, PieceEnum.QUEEN)
    private val notFirstMovementValidator = NotValidator(FirstMovementValidator())
    private val firstMovementValidator = FirstMovementValidator()
    private val emptySquareValidator = EmptySquareToValidator()

    private val firstMovement = AndValidator(
        listOf(
            firstMovementValidator,
            OrValidator(
                listOf(
                    oneStepMovement,
                    twoStepMovement
                )
            ),
            straightMovement,
            emptySquareValidator
        )
    )

    private val notFirstMovement = AndValidator(
        listOf(
            notFirstMovementValidator,
            oneStepMovement,
            straightMovement,
            emptySquareValidator
        )
    )

    private val posibleCapture = AndValidator(
        listOf(
            captureValidator,
            diagonalMovement,
            oneStepMovement
        )
    )

    private val orValidator = OrValidator(
        listOf(
            posibleCapture,
            notFirstMovement,
            firstMovement

        )
    )

    private val andValidator = AndValidator(
        listOf(
            promotionValidator,
            forwardMovement,
            orValidator
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