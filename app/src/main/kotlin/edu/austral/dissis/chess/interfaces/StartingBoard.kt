package edu.austral.dissis.chess.interfaces

import edu.austral.dissis.chess.model.Board
import edu.austral.dissis.chess.model.Player

interface StartingBoard {
    fun generateBoard(): Board
    fun getPlayers(): List<Player>
}