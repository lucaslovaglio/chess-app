package edu.austral.dissis.chess.piece

import edu.austral.dissis.common.piece.PieceType

enum class PieceEnum(val value: String): PieceType {
    PAWN("pawn"),   //Peon
    ROOK("rook"),   //Torre
    KNIGHT("knight"), //Caballo
    BISHOP("bishop"), //Alfil
    QUEEN("queen"),  //Reina
    KING("king")  ;  //Rey
    // no estoy seguro si hacerlo asi porq si cambio la ui tambien deberia cambiar esto

    override fun value(): String {
        return value
    }
//    companion object {
//        private val map = values().associateBy(PieceEnum::name)
//
//        fun fromString(value: String): PieceEnum {
//            return map[value] ?: throw IllegalArgumentException("Piece type is not valid: $value")
//        }
//    }
}