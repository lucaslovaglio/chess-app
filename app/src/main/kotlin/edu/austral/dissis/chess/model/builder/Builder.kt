package edu.austral.dissis.chess.model.builder

import edu.austral.dissis.chess.interfaces.StartingBoard
import edu.austral.dissis.chess.model.Game

interface Builder {
    fun build(startingBoard: StartingBoard): Game
}