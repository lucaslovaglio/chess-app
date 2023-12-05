package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.game.MovementValidator

class CheckValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val nextGame = getNextGame(game, movementData)
        val board = nextGame.board
        val enemyTeam = nextGame.getEnemyTeam(movementData.piece!!.color)
        val enemyPiecesSquares = board.getOccupiedSquaresByTeam(enemyTeam)
        val ownPiecesSquares = board.getOccupiedSquaresByTeam(movementData.piece.color)
        val king = ownPiecesSquares.find { it.piece?.name == PieceEnum.KING }!!
        val movementValidator = MovementValidator()

        for (enemySquare in enemyPiecesSquares) {
            val movementDataAux = MovementData(enemySquare.piece, enemySquare, king)
            val result = movementValidator.validate(movementDataAux, nextGame)
            if (result.isPassed()) {
                return ValidatorResult(ValidatorResultEnum.CHECK)
            }
        }
        return ValidatorResult(ValidatorResultEnum.PASSED)

    }

//    private fun isCheck(
//        game: Game,
//        board: Board,
//        movementData: MovementData,
//        movementValidator: MovementValidator
//    ): Boolean {
//        val enemyTeam = game.getEnemyTeam(movementData.piece!!.color)
//        val enemyPiecesSquares = board.getOccupiedSquaresByTeam(enemyTeam)
//        val ownPiecesSquares = board.getOccupiedSquaresByTeam(movementData.piece.color)
//        val king = ownPiecesSquares.find { it.piece?.name == PieceEnum.KING }!!
//
//        for (enemySquare in enemyPiecesSquares) {
//            val movementDataAux = MovementData(enemySquare.piece, enemySquare, king)
//            val result = movementValidator.validate(movementDataAux, game)
//            if (result.isPassed()) {
//                return false
//            }
//        }
//        return true
//    }

    private fun getNextGame(game: Game, movementData: MovementData): Game {
        val board = game.board.move(movementData)
        return Game(
            board,
            game.validators,
            game.winConditionValidator,
            game.rulesMap,
            game.turnManager
        )
    }
}