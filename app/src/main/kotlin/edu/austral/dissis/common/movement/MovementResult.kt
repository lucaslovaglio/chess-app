package edu.austral.dissis.common.movement

import edu.austral.dissis.common.game.Game

data class MovementResult(
    val result: ResultEnum,
    val message: String,
    val newGame: Game
)
