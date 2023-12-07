package edu.austral.dissis.checkers.game

import edu.austral.dissis.checkers.validator.game.CaptureValidator
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.board.utils.getPossibleMoves
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.game.Player
import edu.austral.dissis.common.game.TurnManager
import edu.austral.dissis.common.movement.MovementData

class CheckersTurnManager(
    private val players: List<Player>,
    private val currentPlayer: Player,
): TurnManager {
    // en cheeckers para saber si comio o no, me puedo fijar si la cantidad de piezas es menor
    // el problema es que como le aseguro que esa pieza es la que tiene que seguir comiendo
    override fun nextTurn(movement: MovementData, game: Game, nextBoard: Board): Game {
        return Game(
            nextBoard,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            getNextTurnManager(movement, game, nextBoard)
//            game.turnManager
        )
    }

    override fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    private fun getNextTurnManager(movement: MovementData, game: Game, nextBoard: Board): TurnManager {
        val gameAfterMove = simulateFutureGame(game, nextBoard)
        if (hasEaten(movement, game) && canStillEating(movement, gameAfterMove))
            return CheckersTurnManager(players, currentPlayer)
        return CheckersTurnManager(players, getNextPlayer())
    }

    private fun hasEaten(movement: MovementData, game: Game): Boolean {
        return CaptureValidator().validate(movement, game).isPassed()
    }

    private fun canStillEating(movement: MovementData, gameAfterMove: Game): Boolean {
        val pieceSquare = getPieceSquare(movement, gameAfterMove)
        val possibleMoves = getPossibleMoves(pieceSquare, gameAfterMove)
        for (move in possibleMoves) {
            if (hasEaten(move, gameAfterMove)) return true
        }
        return false
    }

    private fun getPieceSquare(movement: MovementData, game: Game): Square {
        val board = game.board
        val to = movement.squareTo
        return board.getSquareAt(to.x, to.y)
    }

    private fun simulateFutureGame(game: Game, nextBoard: Board): Game {
        return Game(
            nextBoard,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            CheckersTurnManager(players, currentPlayer)
        )
    }

    private fun getNextPlayer(): Player {
        return players[(players.indexOf(currentPlayer) + 1) % players.size]
    }
}