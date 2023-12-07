package edu.austral.dissis.common.board

import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.piece.Piece

interface GenericBoard {
    fun move(movementData: MovementData): Board
    fun getPieceAt(x: Int, y: Int): Piece?
    fun getSquareAt(x: Int, y: Int): Square
    fun searchPiece(id: Int): Square?
    fun getEmptySquares(): List<Square>
    fun getPieces(): List<Piece>
    fun getNonEmptySquares(): List<Square>
    fun removePieceById(id: Int): Board
    fun removePiece(x: Int, y: Int): Board
    fun addPiece(piece: Piece, x: Int, y: Int): Board
    fun getOccupiedSquaresByTeam(team: ColorEnum): List<Square>
}