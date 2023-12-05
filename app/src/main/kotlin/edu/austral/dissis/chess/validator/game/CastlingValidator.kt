package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.specialAction.CastlingAction
import edu.austral.dissis.common.movement.specialAction.SpecialAction
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CastlingValidator : Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        //TODO: Check if king is in check
        val board = game.board
        val from = movementData.squareFrom
        val to = movementData.squareTo

        if (isValidKingMove(to, from)) {
            val rookDirection = getRookDirection(to, from)
            val rookColumn = getRookColumn(to, from, board)
            val rookFrom = board.getSquareAt(rookColumn, from.y)
            val rookTo = board.getSquareAt( to.x - rookDirection, to.y)
            val rook = board.getPieceAt(rookFrom.x, rookFrom.y)!!

            val allEmpty = areEmptySquaresBetween(rookFrom, rookTo, board)
            val validRookMove = isValidRookMove(board, rookFrom, rookTo)

            if (allEmpty && validRookMove) {
                val specialAction: SpecialAction = CastlingAction(movementData, rook)
                return ValidatorResult(ValidatorResultEnum.PASSED, listOf(specialAction))
            }
        }

        return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
    }

    private fun isValidKingMove(to: Square, from: Square): Boolean {
        val validType = from.piece?.name == PieceEnum.KING
        val validDistance = abs(to.x - from.x) == 2 && to.y == from.y
        val emptySquareTo = to.isEmpty()
        val firstMove = from.piece?.moveQty == 0
        return validType && validDistance && emptySquareTo && firstMove
    }

    private fun getRookDirection(to: Square, from: Square): Int {
        return if (to.x > from.x) 1 else -1
    }

    private fun getRookColumn(to: Square, from: Square, board: Board): Int {
        return if (to.x > from.x) board.width else 1
    }

    private fun isValidRookMove(board: Board, rookFrom: Square, rookTo: Square): Boolean {
        val rook = board.getPieceAt(rookFrom.x, rookFrom.y)
        return rook?.moveQty == 0 && rookTo.isEmpty()
    }


    private fun areEmptySquaresBetween(squareFrom: Square, squareTo: Square, board: Board): Boolean {
        val startX = min(squareFrom.x, squareTo.x)
        val endX = max(squareFrom.x, squareTo.x)
        val y = squareFrom.y
//        val startY = min(squareFrom.y, squareTo.y)
//        val endY = max(squareFrom.y, squareTo.y)

        for (x in startX + 1 until endX) {
//            for (y in startY until endY) {
                val square = board.getSquareAt(x, y)
                if (!square.isEmpty()) {
                    return false
                }
//            }
        }

        return true
    }
}