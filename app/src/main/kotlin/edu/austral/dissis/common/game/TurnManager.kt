package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.MovementData

interface TurnManager { // en cheeckers para saber si comio o no, me puedo fijar si la cantidad de piezas es menor
                        // el problema es que como le aseguro que esa pieza es la que tiene que seguir comiendo
    fun nextTurn(movement: MovementData, game: Game, nextBoard: Board): Game
    fun getCurrentPlayer(): Player
    fun getNextTurnManager(): TurnManager
}