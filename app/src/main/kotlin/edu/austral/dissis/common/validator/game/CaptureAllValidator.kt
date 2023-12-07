package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class CaptureAllValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val board = game.board
        val enemyTeam = game.getEnemyTeam(movementData.piece?.color!!)
        if (board.getOccupiedSquaresByTeam(enemyTeam).isEmpty()) {
            return ValidatorResult(ValidatorResultEnum.PASSED)
        }
        return ValidatorResult(ValidatorResultEnum.NO_CAPTURE_ALL)
    }
}