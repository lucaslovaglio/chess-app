package edu.austral.dissis.common.movement.specialAction

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece

class CastlingAction(private val movementData: MovementData, private val rook: Piece) : SpecialAction {
    override fun execute(board: Board): Board {
        val from = movementData.squareFrom
        val to = movementData.squareTo
        val rookDirection = if (to.x > from.x) 1 else -1
        val rookFrom = board.searchPiece(rook.id)!!
        val rookTo = board.getSquareAt( to.x - rookDirection, to.y)

        // mueve la torre a la posicion correspondiente
        return board.move(MovementData(rook, rookFrom, rookTo))
    }
}