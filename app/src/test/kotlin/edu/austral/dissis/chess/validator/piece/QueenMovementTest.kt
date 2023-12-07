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

class QueenMovementTest {
    @Test
    fun `test queen movement`() {
        val game = createGameWithOnlyQueen(ClassicChess.createGame())
        val board = game.board
        val queenSquare = board.getSquareAt(1, 1) // Assuming the queen is at the starting position

        val validator = QueenMovement()

        // Move diagonally
        assertTrue(validator.validate(diagonalMove(queenSquare, game), game).isPassed())

        // Move horizontally
        assertTrue(validator.validate(horizontalMove(queenSquare, game), game).isPassed())

        // Move vertically
        assertTrue(validator.validate(verticalMove(queenSquare, game), game).isPassed())

        // Move in an L shape (should fail)
        assertFalse(validator.validate(lShapeMove(queenSquare, game), game).isPassed())
    }

    private fun diagonalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 2))
    }

    private fun horizontalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y))
    }

    private fun verticalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun lShapeMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 1))
    }

    private fun createGameWithOnlyQueen(game: Game): Game {
        val queen = Piece(100, ColorEnum.WHITE, PieceEnum.QUEEN, 0)
        val board = Empty8x8Board.generateBoard().addPiece(queen, 1, 1)
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
