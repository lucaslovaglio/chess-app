package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.common.board.utils.getPossibleMoves
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class AnyCaptureValidator: Validator {
    private val captureValidator = CaptureValidator()
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val board = game.board
        val ownPiecesSquares = board.getOccupiedSquaresByTeam(movementData.piece?.color!!)
        for (ownSquare in ownPiecesSquares) {
//            if (ownSquare.piece == movementData.piece) continue
            val possibleMoves = getPossibleMoves(ownSquare, game)
            for (move in possibleMoves) {
                val result = captureValidator.validate(move, game)
                if (result.isPassed()) return ValidatorResult(result.getResult(), result.getSpecialActions())
            }
        }
        return ValidatorResult(ValidatorResultEnum.NO_CAPTURES)
    }
}