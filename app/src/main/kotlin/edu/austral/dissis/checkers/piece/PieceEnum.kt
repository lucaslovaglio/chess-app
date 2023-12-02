package edu.austral.dissis.checkers.piece

import edu.austral.dissis.common.piece.PieceType

enum class PieceEnum(private val value: String): PieceType {
    PAWN("pawn"),
    KING("king");

    override fun value(): String {
        return value
    }
}