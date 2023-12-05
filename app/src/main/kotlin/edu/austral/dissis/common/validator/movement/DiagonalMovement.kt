package edu.austral.dissis.common.validator.movement


import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import kotlin.math.abs

class DiagonalMovement : Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (movementData.squareFrom.x == movementData.squareTo.x || movementData.squareFrom.y == movementData.squareTo.y) {
            return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        }
        val validSquareTo = abs(movementData.squareFrom.x - movementData.squareTo.x) == abs(movementData.squareFrom.y - movementData.squareTo.y)
        val anyObstacle = getDiagonalSquares(movementData, game).any { !it.isEmpty() }
        return if (validSquareTo && !anyObstacle) ValidatorResult(ValidatorResultEnum.PASSED) else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
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
