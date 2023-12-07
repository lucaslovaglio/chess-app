package edu.austral.dissis.common.board.utils

import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData

fun getPossibleMoves(pieceSquare: Square, game: Game): List<MovementData> {
    val squares = game.board.squares
    val posibleMoves = mutableListOf<MovementData>()
    for (square in squares) {
        val movementDataAux = MovementData(pieceSquare.piece, pieceSquare, square)
        if (!game.validate(movementDataAux).isPassed())
            continue
        posibleMoves.add(movementDataAux)
    }
    return posibleMoves
}