package edu.austral.dissis.checkers.game

import edu.austral.dissis.chess.game.ChessTurnManager
import edu.austral.dissis.common.board.Board
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
        val index = players.indexOf(currentPlayer)
        if (game.board.squares.size == nextBoard.squares.size)
            return ChessTurnManager(players, players[(index + 1) % players.size])
        return CheckersTurnManager(players, currentPlayer)
    }
}