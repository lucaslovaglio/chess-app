package edu.austral.dissis.chess.game

import edu.austral.dissis.common.factory.RulesFactory
import edu.austral.dissis.chess.validator.piece.*
import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.game.Player
import edu.austral.dissis.common.game.RulesMap
import edu.austral.dissis.common.game.TurnManager
import edu.austral.dissis.common.movement.MovementData

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