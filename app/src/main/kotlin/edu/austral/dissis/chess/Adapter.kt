package edu.austral.dissis.chess

import edu.austral.dissis.chess.enums.ColorEnum
import edu.austral.dissis.chess.enums.PieceEnum
import edu.austral.dissis.chess.enums.ResultEnum
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.ClassicBoard
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.MovementResult

class Adapter(private var game: Game) : GameEngine {
    override fun applyMove(move: Move): MoveResult {
        val board = game.board
        val xFrom = move.from.column
        val yFrom = move.from.row
        val xTo = move.to.column
        val yTo = move.to.row
        val piece = board.getPieceAt(xFrom, yFrom)
        val movementData = MovementData(piece, board.getSquareAt(xFrom, yFrom), board.getSquareAt(xTo, yTo))
        val result: MovementResult = game.move(movementData)
        return when(result.result) {
            ResultEnum.INVALID_MOVEMENT -> InvalidMove(result.message)
            ResultEnum.VALID_MOVEMENT -> {
                game = result.newGame
                return NewGameState(getPieces(), getCurrentPlayer())
            }
            ResultEnum.GAME_OVER -> GameOver(getCurrentPlayer())
        }
    }

    override fun init(): InitialState {
        return InitialState(
            getBoardSize(),
            getPieces(),
            getCurrentPlayer(),
        )
    }

    private fun getPieces(): List<ChessPiece> {
        val board = game.board
        val pieces = mutableListOf<ChessPiece>()
        for (square in board.getNonEmptySquares()) {
            if (square.piece != null)
                pieces.add(ChessPiece(
                    adaptId(square.piece.id),
                    adaptColor(square.piece.color),
                    adaptPosition(square.y, square.x),
                    adaptPieceType(square.piece.name)
                ))
        }
        return pieces
    }

    private fun adaptPieceType(type: PieceEnum): String {
        return type.value
    }

    private fun adaptPosition(row: Int, column: Int): Position {
        return Position(row, column)
    }

    private fun adaptColor(color: ColorEnum): PlayerColor {
        when(color) {
            ColorEnum.WHITE -> return PlayerColor.WHITE
            ColorEnum.BLACK -> return PlayerColor.BLACK
            else -> throw IllegalArgumentException("Color is not valid")
        }
    }

    private fun getCurrentPlayer(): PlayerColor {
        val player = game.getCurrentPlayer()
        return if (player.color == ColorEnum.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
    }

    private fun getBoardSize(): BoardSize {
        val board = game.board
        val columns = board.width
        val rows = board.height
        return BoardSize(columns, rows)
    }

    private fun adaptId(id: Int): String {
        return id.toString()
    }

    private fun updateGame(newGame: Game) {
        game = newGame
    }
}