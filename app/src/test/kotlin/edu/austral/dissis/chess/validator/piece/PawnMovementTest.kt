package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.factory.ClassicChess
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PawnMovementTest {
    @Test
    fun `test pawn movement`() {
        var game = ClassicChess.createGame()
        val board = game.board
        val whitePawnSquare = board.getSquareAt(1,2)
        val validator = PawnMovement()
        assertTrue(validator.validate(twoStepsForward(whitePawnSquare, game), game).isPassed())
        assertTrue(validator.validate(oneStepForward(whitePawnSquare, game), game).isPassed())
        assertFalse(validator.validate(oneStepBackward(whitePawnSquare, game), game).isPassed())
        game = doAMove(oneStepForward(whitePawnSquare, game), game)
        assertFalse(validator.validate(twoStepsForwardAfterFirstMove(whitePawnSquare, game), game).isPassed())
        game = prepareToEat(whitePawnSquare, game)
        assertTrue(validator.validate(eatingDiagonally(whitePawnSquare, game), game).isPassed())
    }

    private fun oneStepForward(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 1))
    }

    private fun twoStepsForward(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun oneStepBackward(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y - 1))
    }

    private fun twoStepsForwardAfterFirstMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun eatingDiagonally(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 1))
    }

    private fun prepareToEat(from: Square, game: Game): Game {
        val board = game.board
        val blackPawn = board.getSquareAt(2, 7)
        val newBoard = board.move(MovementData(blackPawn.piece, blackPawn, board.getSquareAt(from.x + 1, from.y + 1)))
        return Game(
            newBoard,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            game.turnManager
        )
    }

    private fun doAMove(movementData: MovementData, game: Game): Game {
        val board = game.board
        val newBoard = board.move(movementData)
        return Game(
            newBoard,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            game.turnManager
        )
    }
}