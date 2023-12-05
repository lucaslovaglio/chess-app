package edu.austral.dissis.common.movement.specialAction

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData

interface SpecialAction {
    fun execute(board: Board): Board

}