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

class ArchbishopTest {
    @Test
    fun `test archbishop movement`() {
        val game = createGameWithOnlyArchbishop(ClassicChess.createGame())
        val board = game.board
        val archbishopSquare = board.getSquareAt(4, 1) // Assuming the archbishop is at the starting position

        val validator = Archbishop()

        // Valid bishop-like diagonal move
        assertTrue(validator.validate(diagonalMove(archbishopSquare, game), game).isPassed())

        // Valid knight-like L move
        assertTrue(validator.validate(lMove(archbishopSquare, game), game).isPassed())

        // Invalid move (straight move)
        assertFalse(validator.validate(straightMove(archbishopSquare, game), game).isPassed())

    }

    private fun diagonalMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 2, from.y + 2))
    }

    private fun lMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x + 1, from.y + 2))
    }

    private fun straightMove(from: Square, game: Game): MovementData {
        return MovementData(from.piece, from, game.board.getSquareAt(from.x, from.y + 2))
    }

    private fun createGameWithOnlyArchbishop(game: Game): Game {
        val archbishop = Piece(100, ColorEnum.WHITE, PieceEnum.ARCHBISHOP, 0)
        val board = Empty8x8Board.generateBoard().addPiece(archbishop, 4, 1)
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
