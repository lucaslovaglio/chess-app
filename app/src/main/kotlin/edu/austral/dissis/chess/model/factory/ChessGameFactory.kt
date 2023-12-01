package edu.austral.dissis.chess.model.factory

import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.ChessTurnManager
import edu.austral.dissis.chess.model.ClassicBoard
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.validator.*

object ClassicChess: GameFactory {
    override fun createGame(): Game {
        val startingBoard = ClassicBoard()
        return Game(
            startingBoard.generateBoard(),
            getValidatorList(),
            getWinCondition(),
            ClassicRules.createRulesMap(),
            ChessTurnManager(startingBoard.getPlayers(), startingBoard.getPlayers()[0])
        )
    }

    private fun getValidatorList(): List<Validator> {
        return listOf(
            PositionValidator(),
            EmptySquareValidator(),
            TeamValidator(),
            SelfCaptureValidator(),
            MovementValidator()

        )
    }

    private fun getWinCondition(): Validator {
        return CheckMateValidator()
    }
}