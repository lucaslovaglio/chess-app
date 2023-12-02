package edu.austral.dissis.common.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.MovementRule

class RulesMap(
    private val rulesMap: Map<PieceType, MovementRule>
) {
    fun getRule(pieceType: PieceType): MovementRule {
        return this.rulesMap[pieceType]!!
    }
}