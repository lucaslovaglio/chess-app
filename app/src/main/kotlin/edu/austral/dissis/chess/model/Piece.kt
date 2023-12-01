package edu.austral.dissis.chess.model

import edu.austral.dissis.chess.enums.ColorEnum
import edu.austral.dissis.chess.enums.PieceEnum
import edu.austral.dissis.chess.interfaces.MovementRule

data class Piece( // deberia eliminar esta clase y trabajar directo con el tipo
    val id: Int,
    val color: ColorEnum,
    val name: PieceEnum,
    val moveQty: Int // podria servir para en un futuro agregar nuevas reglas como que si en 50 movimientos no termina empate
) {
    fun canMoveTo(movementData: MovementData): Boolean {
//        return movementRule.validate();
        return true;
    }

}