package edu.austral.dissis.common.adapter

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.PieceType

fun getPieces(game: Game): List<ChessPiece> {
    val board = game.board
    val pieces = mutableListOf<ChessPiece>()
    for (square in board.getNonEmptySquares()) {
        if (square.piece != null)
            pieces.add(
                ChessPiece(
                adaptId(square.piece.id),
                adaptColor(square.piece.color),
                adaptPosition(square.y, square.x),
                adaptPieceType(square.piece.name)
            )
            )
    }
    return pieces
}

fun adaptPieceType(type: PieceType): String {
    return type.value()
}

fun adaptPosition(row: Int, column: Int): Position {
    return Position(row, column)
}

fun adaptColor(color: ColorEnum): PlayerColor {
    return when(color) {
        ColorEnum.WHITE -> PlayerColor.WHITE
        ColorEnum.BLACK -> PlayerColor.BLACK
    }
}

fun getCurrentPlayer(game: Game): PlayerColor {
    val player = game.getCurrentPlayer()
    return if (player.color == ColorEnum.WHITE) PlayerColor.WHITE else PlayerColor.BLACK
}

fun getBoardSize(game: Game): BoardSize {
    val board = game.board
    val columns = board.width
    val rows = board.height
    return BoardSize(columns, rows)
}

fun adaptId(id: Int): String {
    return id.toString()
}

fun adaptMove(game: Game, move: Move): MovementData {
    val board = game.board
    val from = move.from
    val to = move.to
    return MovementData( //la pieza podria no estar aca y tener un metodo en el game
        board.getPieceAt(from.column, from.row),
        board.getSquareAt(from.column, from.row),
        board.getSquareAt(to.column, to.row)
    )
}