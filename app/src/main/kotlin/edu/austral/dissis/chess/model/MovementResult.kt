package edu.austral.dissis.chess.model

import edu.austral.dissis.chess.enums.ResultEnum

data class MovementResult(
    val result: ResultEnum,
    val message: String,
    val newGame: Game
)
