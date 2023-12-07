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

class KnightMovementTest {
    @Test
    fun `test queen movement`() {
        val game = createGameWithOnlyKnight(ClassicChess.createGame())
        val board = game.board
        val knightSquare = board.getSquareAt(1, 1) // Assuming the knight is at the starting position

        val validator = KnightMovement()

        // Move in an L shape
        assertTrue(validator.validate(lShapeMove1(knightSquare, game), game).isPassed())

        // Move in a different L shape
        assertTrue(validator.validate(lShapeMove2(knightSquare, game), game).isPassed())

        // Move in an invalid L shape (should fail)
        assertFalse(validator.validate(invalidLShapeMove(knightSquare, game), game).isPassed())
    }

    private fun lShapeMove1(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 1))
    }

    private fun lShapeMove2(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 2))
    }

    private fun invalidLShapeMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 1))
    }

    private fun createGameWithOnlyKnight(game: Game): Game {
        val knight = Piece(100, ColorEnum.WHITE, PieceEnum.KNIGHT, 0)
        val board = Empty8x8Board.generateBoard().addPiece(knight, 1, 1)
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