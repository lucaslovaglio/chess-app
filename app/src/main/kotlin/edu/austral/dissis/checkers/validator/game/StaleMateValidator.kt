package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.common.board.utils.getPossibleMoves
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class StaleMateValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val board = game.board
        val enemyTeam = game.getEnemyTeam(movementData.piece?.color!!)
        val enemyPiecesSquares = board.getOccupiedSquaresByTeam(enemyTeam)
        for (enemySquare in enemyPiecesSquares) {
            val possibleMoves = getPossibleMoves(enemySquare, game)
            if (possibleMoves.isNotEmpty()) {
                return ValidatorResult(ValidatorResultEnum.NO_STALEMATE)
            }
        }
        return ValidatorResult(ValidatorResultEnum.PASSED)
    }
}