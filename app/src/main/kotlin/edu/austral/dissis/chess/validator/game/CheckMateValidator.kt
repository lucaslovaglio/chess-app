package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.common.board.utils.getPossibleMoves
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class CheckMateValidator: Validator {
    private val checkValidator = CheckValidator()
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val enemyTeam = game.getEnemyTeam(movementData.piece?.color!!)
        if (!checkValidator.validateForTeam(game, movementData, enemyTeam)) {
            return ValidatorResult(ValidatorResultEnum.NO_CHECKMATE)
        }
        val board = game.board
        val ownPiecesSquares = board.getOccupiedSquaresByTeam(enemyTeam)
//        val squares = board.squares
        var isPassed = true
        for (ownPieceSquare in ownPiecesSquares) {
            val possibleMoves = getPossibleMoves(ownPieceSquare, game)
//            if (possibleMoves.isEmpty()) return ValidatorResult(ValidatorResultEnum.PASSED)
            for (move in possibleMoves) {
//                val movementDataAux = MovementData(ownPieceSquare.piece, ownPieceSquare, square)
//                if (!game.validate(movementDataAux).isPassed())
//                    continue
                val result = checkValidator.validate(move, game)
                isPassed = isPassed && !result.isPassed()
            }
        }
        return if (isPassed) {
            ValidatorResult(ValidatorResultEnum.PASSED)
        } else ValidatorResult(ValidatorResultEnum.NO_CHECKMATE)
    }
}