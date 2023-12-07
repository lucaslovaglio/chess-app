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

class KingMovementTest {
    @Test
    fun `test king movement`() {
        val game = createGameWithOnlyKing(ClassicChess.createGame())
        val board = game.board
        val kingSquare = board.getSquareAt(5, 1) // Assuming the king is at the starting position

        val validator = KingMovement()

        // Move one step in any direction
        assertTrue(validator.validate(oneStepMove(kingSquare, game), game).isPassed())

        // Try an invalid move (two steps)
        assertFalse(validator.validate(twoStepsMove(kingSquare, game), game).isPassed())

        // Try an invalid move (diagonal two steps)
        assertFalse(validator.validate(diagonalTwoStepsMove(kingSquare, game), game).isPassed())

        // Try castling (assuming valid castling position)
        assertTrue(validator.validate(castlingMove(kingSquare, game), game).isPassed())
    }

    private fun oneStepMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y))
    }

    private fun twoStepsMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun diagonalTwoStepsMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 2))
    }

    private fun castlingMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(7, 1))
    }

    private fun createGameWithOnlyKing(game: Game): Game {
        val king = Piece(100, ColorEnum.WHITE, PieceEnum.KING, 0)
        val rook = Piece(200, ColorEnum.WHITE, PieceEnum.ROOK, 0)
        val board = Empty8x8Board.generateBoard().addPiece(king, 5, 1).addPiece(rook, 8, 1)
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
