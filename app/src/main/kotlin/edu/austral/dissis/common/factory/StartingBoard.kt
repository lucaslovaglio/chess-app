package edu.austral.dissis.common.factory

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Player

interface StartingBoard {
    fun generateBoard(): Board
    fun getPlayers(): List<Player>
}