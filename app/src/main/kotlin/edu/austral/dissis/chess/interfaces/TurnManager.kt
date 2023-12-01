package edu.austral.dissis.chess.interfaces

import edu.austral.dissis.chess.model.Board
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.Player
import edu.austral.dissis.chess.model.builder.GameBuilder

interface TurnManager { // en cheeckers para saber si comio o no, me puedo fijar si la cantidad de piezas es menor
    fun nextTurn(movement: MovementData, game: Game, nextBoard: Board): Game
    fun getCurrentPlayer(): Player
}