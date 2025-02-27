package edu.austral.dissis.chess.validator.movement

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum

class LinearMovement(private val isHorizontal: Boolean) : Validator {

    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        if (notValidSquareTo(movementData, isHorizontal)) return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
        val board: Board = game.board
        val sameLine: (Square) -> Boolean = if (isHorizontal) { square -> isSquareOnSameLine(square, movementData.squareFrom, isHorizontal) } else { square -> isSquareOnSameLine(square, movementData.squareFrom, isHorizontal) }
        val isBetween: (Square) -> Boolean = { isSquareBetween(it, movementData.squareFrom, movementData.squareTo, isHorizontal) }

        return if (board.squares.none { sameLine(it) && isBetween(it) && !it.isEmpty() })
                    ValidatorResult(ValidatorResultEnum.PASSED)
                else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }

    private fun notValidSquareTo(movementData: MovementData, isHorizontal: Boolean): Boolean {
        return if (isHorizontal) movementData.squareFrom.y != movementData.squareTo.y else movementData.squareFrom.x != movementData.squareTo.x
    }

    private fun isSquareBetween(square: Square, from: Square, to: Square, isHorizontal: Boolean): Boolean {
        return if (isHorizontal) {
            (from.x < square.x && square.x < to.x) || (from.x > square.x && square.x > to.x)
        } else {
            (from.y < square.y && square.y < to.y) || (from.y > square.y && square.y > to.y)
        }
    }

    private fun isSquareOnSameLine(square: Square, from: Square, isHorizontal: Boolean): Boolean {
        return if (isHorizontal) {
            square.y == from.y
        } else {
            square.x == from.x
        }
    }
}
