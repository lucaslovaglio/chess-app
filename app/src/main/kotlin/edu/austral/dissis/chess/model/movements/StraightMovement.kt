package edu.austral.dissis.chess.model.movements

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Board
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.Square

class StraightMovement : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val board: Board = game.board
        val sameColumn: (Square) -> Boolean = { it.x == movementData.squareFrom.x }
        val isBetween: (Square) -> Boolean = { isSquareBetween(it, movementData.squareFrom, movementData.squareTo) }

        return board.squares.none { sameColumn(it) && isBetween(it) && !it.isEmpty() }
    }

    private fun isSquareBetween(square: Square, from: Square, to: Square): Boolean {
        return (from.y < square.y && square.y < to.y) || (from.y > square.y && square.y > to.y)
    }

}
