package edu.austral.dissis.chess.factory


import edu.austral.dissis.chess.game.ChessTurnManager
import edu.austral.dissis.chess.validator.game.CheckMateValidator
import edu.austral.dissis.common.factory.GameFactory
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.game.*

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