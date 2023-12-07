package edu.austral.dissis.common.adapter

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.movement.MovementResult
import edu.austral.dissis.common.movement.ResultEnum


class Adapter(private var game: Game) : GameEngine {
    override fun applyMove(move: Move): MoveResult {
        val movementData = adaptMove(game, move)
        val result: MovementResult = game.move(movementData)
        return when(result.result) {
            ResultEnum.INVALID_MOVEMENT -> InvalidMove(result.message)
            ResultEnum.VALID_MOVEMENT -> {
                updateGame(result.newGame)
                return NewGameState(getPieces(game), getCurrentPlayer(game))
            }
            ResultEnum.GAME_OVER -> GameOver(getCurrentPlayer(game))
        }
    }

    override fun init(): InitialState {
        return InitialState(
            getBoardSize(game),
            getPieces(game),
            getCurrentPlayer(game),
        )
    }

    private fun updateGame(newGame: Game) {
        game = newGame
    }
}