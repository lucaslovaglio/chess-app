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

class BishopMovementTest {
    @Test
    fun `test bishop movement`() {
        val game = createGameWithOnlyBishop(ClassicChess.createGame())
        val board = game.board
        val bishopSquare = board.getSquareAt(3, 1) // Assuming the bishop is at the starting position

        val validator = BishopMovement()

        // Valid diagonal move
        assertTrue(validator.validate(diagonalMove(bishopSquare, game), game).isPassed())

        // Invalid move (straight move)
        assertFalse(validator.validate(straightMove(bishopSquare, game), game).isPassed())
    }

    private fun diagonalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 2))
    }

    private fun straightMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun createGameWithOnlyBishop(game: Game): Game {
        val bishop = Piece(100, ColorEnum.WHITE, PieceEnum.BISHOP, 0)
        val board = Empty8x8Board.generateBoard().addPiece(bishop, 3, 1)
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
