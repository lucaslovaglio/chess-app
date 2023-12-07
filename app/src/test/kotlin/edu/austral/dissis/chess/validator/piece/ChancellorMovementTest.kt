package edu.austral.dissis.chess.validator.piece

import edu.austral.dissis.chess.factory.ClassicChess
import edu.austral.dissis.chess.factory.Empty8x8Board
import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.board.Square
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChancellorMovementTest {
    @Test
    fun `test chancellor movement`() {
        val game = createGameWithOnlyChancellor(ClassicChess.createGame())
        val board = game.board
        val chancellorSquare = board.getSquareAt(1, 1) // Assuming the chancellor is at the starting position

        val validator = ChancellorMovement()

        // Move like a rook
        assertTrue(validator.validate(rookMove(chancellorSquare, game), game).isPassed())

        // Move like a knight
        assertTrue(validator.validate(knightMove(chancellorSquare, game), game).isPassed())

        // Try an invalid move (diagonal two steps)
        assertFalse(validator.validate(diagonalTwoStepsMove(chancellorSquare, game), game).isPassed())
    }

    private fun rookMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y))
    }

    private fun knightMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 2))
    }

    private fun diagonalTwoStepsMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 1))
    }

    private fun createGameWithOnlyChancellor(game: Game): Game {
        val chancellor = Piece(100, ColorEnum.WHITE, PieceEnum.CHANCELLOR, 0)
        val board = Empty8x8Board.generateBoard().addPiece(chancellor, 1, 1)
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
