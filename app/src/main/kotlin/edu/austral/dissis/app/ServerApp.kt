package edu.austral.dissis.app

import edu.austral.dissis.checkers.factory.ClassicCheckers
import edu.austral.dissis.chess.factory.ClassicChess
import edu.austral.dissis.server.GameServer

fun main() {
    GameServer(ClassicChess.createGame())
}