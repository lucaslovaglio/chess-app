package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.game.CheckersTurnManager
import edu.austral.dissis.checkers.validator.game.AnyCaptureValidator
import edu.austral.dissis.checkers.validator.game.StaleMateValidator
import edu.austral.dissis.common.factory.GameFactory
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.game.*

object ClassicCheckers: GameFactory {
    override fun createGame(): Game {
        val startingBoard = ClassicBoard()
        return Game(
            startingBoard.generateBoard(),
            getValidatorList(),
            getWinCondition(),
            ClassicRules.createRulesMap(),
            CheckersTurnManager(startingBoard.getPlayers(), startingBoard.getPlayers()[0])
        )
    }

    private fun getValidatorList(): List<Validator> {
        return listOf(
            PositionValidator(),
            EmptySquareValidator(),
            TeamValidator(),
            SelfCaptureValidator(),
            MovementValidator(),
            AnyCaptureValidator()
        )
    }

    private fun getWinCondition(): Validator {
//        return CaptureAllValidator()
        return StaleMateValidator()
    }
}