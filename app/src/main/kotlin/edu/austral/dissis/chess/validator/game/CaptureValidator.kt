package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.ValidatorResult

class CaptureValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (!movementData.squareTo.isEmpty() && isAnEnemy(movementData.piece, movementData.squareTo)) {
            return ValidatorResult(ValidatorResultEnum.PASSED)
        }
        return ValidatorResult(ValidatorResultEnum.INVALID_CAPTURE)
    }

    private fun isAnEnemy(piece: Piece?, square: Square): Boolean {
        return piece?.color != square.piece?.color
    }
}