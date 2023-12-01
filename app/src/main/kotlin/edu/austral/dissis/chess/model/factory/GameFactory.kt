package edu.austral.dissis.chess.model.factory

import edu.austral.dissis.chess.model.Game

interface GameFactory {
    fun createGame(): Game
}