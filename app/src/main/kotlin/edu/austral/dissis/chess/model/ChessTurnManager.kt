package edu.austral.dissis.chess.model

import edu.austral.dissis.chess.interfaces.TurnManager

class ChessTurnManager(
    private val players: List<Player>,
    private val currentPlayer: Player,
    ): TurnManager {
    override fun nextTurn(movement: MovementData, game: Game, nextBoard: Board): Game {
        val index = players.indexOf(currentPlayer)
        val nextTurn: TurnManager = ChessTurnManager(players, players[(index + 1) % players.size])
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
}