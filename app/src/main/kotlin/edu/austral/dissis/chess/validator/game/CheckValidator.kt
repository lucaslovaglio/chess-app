package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.game.MovementValidator

class CheckValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        return if (validateForTeam(game, movementData, movementData.piece?.color!!)) {
            ValidatorResult(ValidatorResultEnum.CHECK)
        } else ValidatorResult(ValidatorResultEnum.PASSED)
    }

    fun validateForTeam(
        game: Game,
        movementData: MovementData,
        colorInCheck: ColorEnum
    ): Boolean {
        val nextGame = getNextGame(game, movementData)
        return isCheck(nextGame, colorInCheck)
    }

    fun isCheck(
        game: Game,
        colorInCheck: ColorEnum
    ): Boolean {
        val board = game.board
        val enemyTeam = game.getEnemyTeam(colorInCheck)
        val enemyPiecesSquares = board.getOccupiedSquaresByTeam(enemyTeam)
        val ownPiecesSquares = board.getOccupiedSquaresByTeam(colorInCheck)
        val king = ownPiecesSquares.find { it.piece?.name == PieceEnum.KING } ?: return false

        for (enemySquare in enemyPiecesSquares) {
            val movementDataAux = MovementData(enemySquare.piece, enemySquare, king)
            if (movementDataAux.piece?.name == PieceEnum.KING) {
                continue
            }
            val result = MovementValidator().validate(movementDataAux, game)
            if (result.isPassed()) {
                return true
            }
        }
        return false
    }

    private fun getNextGame(game: Game, movementData: MovementData): Game {
        val board = game.board.move(movementData)
        val turnManager = game.turnManager
        return turnManager.nextTurn(movementData, game, board)
//        return Game(
//            board,
//            game.validators,
//            game.winConditionValidator,
//            game.rulesMap,
//            game.turnManager.getNextTurnManager(movementData, game, board)
//        )
    }
}