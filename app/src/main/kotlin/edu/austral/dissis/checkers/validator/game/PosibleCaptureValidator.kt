package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class PosibleCaptureValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val board = game.board
        val piece = movementData.piece
//        for (square in board.squares) {
//
//        }
        return ValidatorResult(ValidatorResultEnum.PASSED) // TODO
    }
}