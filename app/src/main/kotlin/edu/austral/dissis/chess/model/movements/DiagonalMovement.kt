package edu.austral.dissis.chess.model.movements

import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.Square
import kotlin.math.abs

class DiagonalMovement : MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val validSquareTo = abs(movementData.squareFrom.x - movementData.squareTo.x) == abs(movementData.squareFrom.y - movementData.squareTo.y)
        val anyObstacle = getDiagonalSquares(movementData, game).any { !it.isEmpty() }
        return validSquareTo && !anyObstacle
    }

    private fun getDiagonalSquares(movementData: MovementData, game: Game): List<Square> {
        val board = game.board
        val squares = mutableListOf<Square>()
        val x = movementData.squareFrom.x
        val y = movementData.squareFrom.y
        val xDirection = if (movementData.squareTo.x > movementData.squareFrom.x) 1 else -1
        val yDirection = if (movementData.squareTo.y > movementData.squareFrom.y) 1 else -1
        var currentX = x + xDirection
        var currentY = y + yDirection
        while (currentX != movementData.squareTo.x && currentY != movementData.squareTo.y) {
            squares.add(board.getSquareAt(currentX, currentY))
            currentX += xDirection
            currentY += yDirection
        }
        return squares
    }
}
