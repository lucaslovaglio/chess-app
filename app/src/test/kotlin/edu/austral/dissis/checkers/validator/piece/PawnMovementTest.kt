package edu.austral.dissis.checkers.validator.piece

import edu.austral.dissis.checkers.factory.ClassicCheckers
import edu.austral.dissis.checkers.factory.Empty8x8Board
import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PawnMovementTest {
    @Test
    fun `test pawn movement in checkers`() {
        val game = createGameWithOnlyPawn(ClassicCheckers.createGame())
        val board = game.board
        val pawnSquare = board.getSquareAt(2, 4) // Assuming the pawn is at the starting position

        val validator = PawnMovement()

        // Valid forward move
        assertTrue(validator.validate(forwardMove(pawnSquare, game), game).isPassed())

        // Invalid backward move
        assertFalse(validator.validate(backwardMove(pawnSquare, game), game).isPassed())

        // Invalid non-diagonal move
        assertFalse(validator.validate(nonDiagonalMove(pawnSquare, game), game).isPassed())

        // Invalid two steps move
        assertFalse(validator.validate(twoStepsMove(pawnSquare, game), game).isPassed())

        // Valid capture move
        val newGame = createScenarioForCaptureMove(game)
        assertTrue(validator.validate(captureMove(pawnSquare, newGame), newGame).isPassed())
    }

    private fun forwardMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 1))
    }

    private fun backwardMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x - 1, from.y - 1))
    }

    private fun captureMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 2))
    }

    private fun nonDiagonalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y))
    }

    private fun twoStepsMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 2))
    }

    private fun createGameWithOnlyPawn(game: Game): Game {
        val pawn = Piece(100, ColorEnum.WHITE, PieceEnum.PAWN, 0)
        val board = Empty8x8Board.generateBoard().addPiece(pawn, 2, 4)
        return Game(
            board,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            game.turnManager
        )
    }

    private fun createScenarioForCaptureMove(game: Game): Game {
        val board = game.board.addPiece(Piece(100, ColorEnum.BLACK, PieceEnum.PAWN, 0), 3, 5)
        return Game(
            board,
            game.validator,
            game.specialRule,
            game.winConditionValidator,
            game.rulesMap,
            game.turnManager
        )
    }
}
