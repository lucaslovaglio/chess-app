package edu.austral.dissis.common.movement.specialAction

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceType

class PromotionAction(
    private val movementData: MovementData,
    private val pieceTo: PieceType
): SpecialAction {
    override fun execute(board: Board): Board {
        val to = movementData.squareTo
        val piece = movementData.piece!!
        val newPiece = piece.copy(name = pieceTo)
        return board
            .removePiece(to.x, to.y)
            .addPiece(newPiece, to.x, to.y)
    }
}