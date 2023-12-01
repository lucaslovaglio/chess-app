package edu.austral.dissis.common.factory

import edu.austral.dissis.common.game.Game

interface GameFactory {
    fun createGame(): Game
}