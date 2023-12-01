package edu.austral.dissis.chess.enums

enum class PieceEnum(val value: String) {
    PAWN("pawn"),   //Peon
    ROOK("rook"),   //Torre
    KNIGHT("knight"), //Caballo
    BISHOP("bishop"), //Alfil
    QUEEN("queen"),  //Reina
    KING("king")  ;  //Rey
    // no estoy seguro si hacerlo asi porq si cambio la ui tambien deberia cambiar esto

//    companion object {
//        private val map = values().associateBy(PieceEnum::name)
//
//        fun fromString(value: String): PieceEnum {
//            return map[value] ?: throw IllegalArgumentException("Piece type is not valid: $value")
//        }
//    }
}