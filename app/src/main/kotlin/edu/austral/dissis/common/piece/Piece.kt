package edu.austral.dissis.common.piece

import edu.austral.dissis.chess.piece.PieceEnum

data class Piece( // deberia eliminar esta clase y trabajar directo con el tipo
    val id: Int,
    val color: ColorEnum,
    val name: PieceType,
    val moveQty: Int // podria servir para en un futuro agregar nuevas reglas como que si en 50 movimientos no termina empate
) {
//    fun canMoveTo(movementData: MovementData): Boolean {
////        return movementRule.validate();
//        return true;
//    }

}