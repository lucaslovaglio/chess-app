package edu.austral.dissis.common.adapter

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.MovementResult
import edu.austral.dissis.common.movement.ResultEnum
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.PieceType


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
//        return InitialState(boardSize=BoardSize(columns=8, rows=8), pieces=mutableListOf<ChessPiece>(ChessPiece(id="2", color=PlayerColor.WHITE, position=Position(row=1, column=2), pieceId="pawn"), ChessPiece(id="4", color=PlayerColor.WHITE, position=Position(row=1, column=4), pieceId="pawn"), ChessPiece(id="6", color=PlayerColor.WHITE, position=Position(row=1, column=6), pieceId="pawn"), ChessPiece(id="8", color=PlayerColor.WHITE, position=Position(row=1, column=8), pieceId="pawn"), ChessPiece(id="1", color=PlayerColor.WHITE, position=Position(row=2, column=1), pieceId="pawn"), ChessPiece(id="3", color=PlayerColor.WHITE, position=Position(row=2, column=3), pieceId="pawn"), ChessPiece(id="5", color=PlayerColor.WHITE, position=Position(row=2, column=5), pieceId="pawn"), ChessPiece(id="7", color=PlayerColor.WHITE, position=Position(row=2, column=7), pieceId="pawn"), ChessPiece(id="2", color=PlayerColor.WHITE, position=Position(row=3, column=2), pieceId="pawn"), ChessPiece(id="4", color=PlayerColor.WHITE, position=Position(row=3, column=4), pieceId="pawn"), ChessPiece(id="6", color=PlayerColor.WHITE, position=Position(row=3, column=6), pieceId="pawn"), ChessPiece(id="8", color=PlayerColor.WHITE, position=Position(row=3, column=8), pieceId="pawn"), ChessPiece(id="1", color=PlayerColor.BLACK, position=Position(row=6, column=1), pieceId="pawn"), ChessPiece(id="3", color=PlayerColor.BLACK, position=Position(row=6, column=3), pieceId="pawn"), ChessPiece(id="5", color=PlayerColor.BLACK, position=Position(row=6, column=5), pieceId="pawn"), ChessPiece(id="7", color=PlayerColor.BLACK, position=Position(row=6, column=7), pieceId="pawn"), ChessPiece(id="2", color=PlayerColor.BLACK, position=Position(row=7, column=2), pieceId="pawn"), ChessPiece(id="4", color=PlayerColor.BLACK, position=Position(row=7, column=4), pieceId="pawn"), ChessPiece(id="6", color=PlayerColor.BLACK, position=Position(row=7, column=6), pieceId="pawn"), ChessPiece(id="8", color=PlayerColor.BLACK, position=Position(row=7, column=8), pieceId="pawn"), ChessPiece(id="1", color=PlayerColor.BLACK, position=Position(row=8, column=1), pieceId="pawn"), ChessPiece(id="3", color=PlayerColor.BLACK, position=Position(row=8, column=3), pieceId="pawn"), ChessPiece(id="5", color=PlayerColor.BLACK, position=Position(row=8, column=5), pieceId="pawn"), ChessPiece(id="7", color=PlayerColor.BLACK, position=Position(row=8, column=7), pieceId="pawn")), currentPlayer=PlayerColor.WHITE)
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

    private fun adaptPieceType(type: PieceType): String {
        return type.value()
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