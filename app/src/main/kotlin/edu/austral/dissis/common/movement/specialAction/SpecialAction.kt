package edu.austral.dissis.common.movement.specialAction

import edu.austral.dissis.common.board.Board

interface SpecialAction {
    fun execute(board: Board): Board

}