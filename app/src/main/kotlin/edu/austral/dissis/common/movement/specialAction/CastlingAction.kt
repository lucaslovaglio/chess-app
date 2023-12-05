package edu.austral.dissis.common.movement.specialAction

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece

class CastlingAction(private val movementData: MovementData, val rook: Piece) : SpecialAction {
    override fun execute(board: Board): Board {
        val from = movementData.squareFrom
        val to = movementData.squareTo
        val rookDirection = if (to.x > from.x) 1 else -1
//        val rookColumn = if (to.x > from.x) board.width else 1
//        val rookFrom = Square(null, rookColumn, from.y)
        val rookFrom = board.searchPiece(rook.id)!!
        val rookTo = board.getSquareAt( to.x - rookDirection, to.y)
//        val rook = board.getPieceAt(rookFrom.x, rookFrom.y)

        // Mover la torre a la posición correspondiente
        return board.move(MovementData(rook, rookFrom, rookTo))
    }
}