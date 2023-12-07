package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.game.CheckersTurnManager
import edu.austral.dissis.checkers.validator.game.CaptureIfPossibleValidator
import edu.austral.dissis.checkers.validator.game.StaleMateValidator
import edu.austral.dissis.common.factory.GameFactory
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.composite.AndValidator
import edu.austral.dissis.common.validator.game.*

object ClassicCheckers: GameFactory {
    override fun createGame(): Game {
        val startingBoard = ClassicBoard
        return Game(
            startingBoard.generateBoard(),
            getValidator(),
            getSpecialRule(),
            getWinCondition(),
            ClassicRules.createRulesMap(),
            CheckersTurnManager(startingBoard.getPlayers(), startingBoard.getPlayers()[0])
        )
    }

    private fun getValidator(): Validator {
        return AndValidator(listOf(
            PositionValidator(),
            EmptySquareValidator(),
            TeamValidator(),
            SelfCaptureValidator(),
            MovementValidator()
        ))

    }

    private fun getSpecialRule(): Validator {
        return AndValidator(listOf(
            CaptureIfPossibleValidator(),
        ))
    }

    private fun getWinCondition(): Validator {
        return AndValidator(listOf(
            CaptureAllValidator(),
            StaleMateValidator()
        ))
    }
}