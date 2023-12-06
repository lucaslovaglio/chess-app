package edu.austral.dissis.checkers.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.game.Player
import edu.austral.dissis.common.game.TurnManager
import edu.austral.dissis.common.movement.MovementData

class CheckersTurnManager(
    private val players: List<Player>,
    private val currentPlayer: Player,
): TurnManager {
    override fun nextTurn(movement: MovementData, game: Game, nextBoard: Board): Game {
        val index = players.indexOf(currentPlayer)
        val nextTurn: TurnManager = CheckersTurnManager(players, players[(index + 1) % players.size])
        return Game(
            nextBoard,
            game.validators,
            game.winConditionValidator,
            game.rulesMap,
            nextTurn
        )
    }

    override fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    override fun getNextTurnManager(): TurnManager {
        TODO("Not yet implemented")
    }
}