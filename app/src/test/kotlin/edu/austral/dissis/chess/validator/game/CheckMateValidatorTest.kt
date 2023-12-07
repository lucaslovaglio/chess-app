package edu.austral.dissis.chess.validator.game

import edu.austral.dissis.chess.factory.ClassicChess
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.ValidatorResultEnum
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CheckMateValidatorTest {
    @Test
    fun `test checkmate validator`() {
        // Create a game with a checkmate scenario
        val game = createCheckmateScenario()
        val board = game.board

        // Validate the checkmate
        val validator = CheckMateValidator()
        val result = validator.validate(MovementData(board.getPieceAt(4, 8), board.getSquareAt(4, 8), board.getSquareAt(8, 4)), game)

        // Check if the result is as expected
        assertEquals(ValidatorResultEnum.PASSED, result.getResult())
    }

    private fun createCheckmateScenario(): Game {
        val game = ClassicChess.createGame()


        // Move pieces to create a checkmate scenario
        val board = game.board
        return game.move(MovementData(board.getPieceAt(6, 2), board.getSquareAt(6, 2), board.getSquareAt(6, 3)))
            .newGame.move(MovementData(board.getPieceAt(5, 7), board.getSquareAt(5, 7), board.getSquareAt(5, 6)))
            .newGame.move(MovementData(board.getPieceAt(7, 2), board.getSquareAt(7, 2), board.getSquareAt(7, 4)))
            .newGame
    }
}
