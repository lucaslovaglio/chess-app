package edu.austral.dissis.chess.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.game.Player
import edu.austral.dissis.common.game.TurnManager
import edu.austral.dissis.common.movement.MovementData

class ChessTurnManager(
    private val players: List<Player>,
    private val currentPlayer: Player,
): TurnManager {
    override fun nextTurn(movement: MovementData, game: Game, nextBoard: Board): Game {
        return Game(
            nextBoard,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            getNextTurnManager()
        )
    }

    override fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    private fun getNextTurnManager(): TurnManager {
        val index = players.indexOf(currentPlayer)
        return ChessTurnManager(players, players[(index + 1) % players.size])
    }
}