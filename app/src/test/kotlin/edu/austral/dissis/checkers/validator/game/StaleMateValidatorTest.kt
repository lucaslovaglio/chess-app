package edu.austral.dissis.checkers.validator.game

import edu.austral.dissis.checkers.factory.ClassicCheckers
import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.validator.ValidatorResultEnum
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StaleMateValidatorTest {
    @Test
    fun `test staleMate in checkers`() {
        val game = createGameForStaleMate(ClassicCheckers.createGame())
        val board = game.board

        // Validate the checkmate
        val validator = StaleMateValidator()
        val result = validator.validate(MovementData(board.getPieceAt(7, 2), board.getSquareAt(7, 2), board.getSquareAt(6, 3)), game)
        assertEquals(ValidatorResultEnum.PASSED, result.getResult())
    }

    private fun createGameForStaleMate(game: Game): Game {
        val board = game.board
            .addPiece(Piece(100, ColorEnum.BLACK, PieceEnum.PAWN, 0), 8, 5)
            .addPiece(Piece(100, ColorEnum.WHITE, PieceEnum.PAWN, 0), 7, 4)
            .addPiece(Piece(100, ColorEnum.WHITE, PieceEnum.PAWN, 0), 7, 2)
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
