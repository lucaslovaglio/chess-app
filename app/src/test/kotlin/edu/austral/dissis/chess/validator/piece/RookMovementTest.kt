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

class RookMovementTest{
    @Test
    fun `test rook movement`(){
        val game = createGameWithOnlyRook(ClassicChess.createGame())
        val board = game.board
        val rookSquare = board.getSquareAt(1, 1)

        val validator = RookMovement()

        // Move vertically
        assertTrue(validator.validate(verticalMove(rookSquare, game), game).isPassed())

        // Move horizontally
        assertTrue(validator.validate(horizontalMove(rookSquare, game), game).isPassed())

        // Move diagonally (should fail)
        assertFalse(validator.validate(diagonalMove(rookSquare, game), game).isPassed())
    }

    private fun verticalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun horizontalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y))
    }

    private fun diagonalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 1))
    }


    private fun createGameWithOnlyRook(game: Game): Game {
        val rook = Piece(100, ColorEnum.WHITE, PieceEnum.ROOK, 0)
        val board = Empty8x8Board.generateBoard().addPiece(rook, 1, 1)
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