package edu.austral.dissis.common.board.utils

import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData

fun getPossibleMoves(pieceSquare: Square, game: Game): List<MovementData> {
    val squares = game.board.squares
    val posibleMoves = mutableListOf<MovementData>()
    for (square in squares) {
        val movementDataAux = MovementData(pieceSquare.piece, pieceSquare, square)
        if (!isValid(movementDataAux, game))
            continue
        posibleMoves.add(movementDataAux)
    }
    return posibleMoves
}

private fun isValid(movementData: MovementData, game: Game): Boolean {
    val validator = game.validator
    return validator.validate(movementData, game).isPassed()
}
