package edu.austral.dissis.chess.model.builder

import edu.austral.dissis.chess.interfaces.StartingBoard
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.*
import edu.austral.dissis.chess.model.validator.CheckMateValidator

class GameBuilder(): Builder  {

    override fun build(startingBoard: StartingBoard): Game {

        val board = startingBoard.generateBoard()
        val validators = listOf<Validator>()
        val winConditionValidator = CheckMateValidator()
        val rulesMap = RulesMap(mapOf())
        val players = startingBoard.getPlayers()
        val turnManager = ChessTurnManager(players, players[0])

        return Game(board, validators, winConditionValidator, rulesMap, turnManager)
    }

}