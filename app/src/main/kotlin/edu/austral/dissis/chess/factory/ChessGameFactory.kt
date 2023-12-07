package edu.austral.dissis.chess.factory


import edu.austral.dissis.chess.game.ChessTurnManager
import edu.austral.dissis.chess.validator.game.CheckMateValidator
import edu.austral.dissis.chess.validator.game.CheckValidator
import edu.austral.dissis.common.factory.GameFactory
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.game.*

object ClassicChess: GameFactory {
    override fun createGame(): Game {
        val startingBoard = ClassicBoard()
        return Game(
            startingBoard.generateBoard(),
            getValidator(),
            getSpecialRule(),
            getWinCondition(),
            ClassicRules.createRulesMap(),
            ChessTurnManager(startingBoard.getPlayers(), startingBoard.getPlayers()[0])
        )
    }

    private fun getValidator(): Validator {
        return AndValidator(listOf(
            PositionValidator(),
            EmptySquareValidator(),
            TeamValidator(),
            SelfCaptureValidator(),
            MovementValidator(),
            CheckValidator()
        ))
    }

    private fun getSpecialRule(): Validator {
        return AndValidator(listOf())
    }

    private fun getWinCondition(): Validator {
        return AndValidator(listOf(
            CheckMateValidator()
        ))
    }
}