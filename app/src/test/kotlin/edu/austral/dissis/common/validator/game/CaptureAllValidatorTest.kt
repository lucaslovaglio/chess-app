package edu.austral.dissis.common.validator.game

import edu.austral.dissis.checkers.factory.ClassicCheckers
import edu.austral.dissis.checkers.factory.Empty8x8Board
import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.ResultEnum
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CaptureAllValidatorTest {
    @Test
    fun `test captureAll in checkers`() {
        val game = createGameForCaptureAll(ClassicCheckers.createGame())
        val board = game.board

        // Validate the checkmate
        val newGame = game.move(MovementData(board.getPieceAt(5, 4), board.getSquareAt(5, 4), board.getSquareAt(3, 6)))
//        val result = validator.validate(MovementData(board.getPieceAt(5, 4), board.getSquareAt(5, 4), board.getSquareAt(3, 6)), newGame)
        assertEquals(ResultEnum.GAME_OVER, newGame.result)
    }

    private fun createGameForCaptureAll(game: Game): Game {
        val board = Empty8x8Board.generateBoard()
            .addPiece(Piece(100, ColorEnum.BLACK, PieceEnum.PAWN, 0), 4, 5)
            .addPiece(Piece(200, ColorEnum.WHITE, PieceEnum.PAWN, 0), 5, 4)
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