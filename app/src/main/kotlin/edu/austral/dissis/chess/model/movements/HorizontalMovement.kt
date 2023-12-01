package edu.austral.dissis.chess.model.movements

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Board
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.Square

class HorizontalMovement : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val board: Board = game.board
        val sameRow: (Square) -> Boolean = { it.y == movementData.squareFrom.y }
        val isBetween: (Square) -> Boolean = { isSquareBetween(it, movementData.squareFrom, movementData.squareTo) }

        // checkea que cada square ocupado, ubicado entre from y to (horizontalmente) este vacio
        return board.squares.none { sameRow(it) && isBetween(it) && !it.isEmpty() }
    }

    private fun isSquareBetween(square: Square, from: Square, to: Square): Boolean {
        return (from.x < square.x && square.x < to.x) || (from.x > square.x && square.x > to.x)
    }

}
