package edu.austral.dissis.checkers.movement.specialAction

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.movement.specialAction.SpecialAction

class CaptureMiddleAction(private val middlePiece: Square): SpecialAction {
    override fun execute(board: Board): Board {
        return board.removePiece(middlePiece.x, middlePiece.y)
    }
}