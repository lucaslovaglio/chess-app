package edu.austral.dissis.common.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.validator.MovementRule

class RulesMap(
    private val rulesMap: Map<PieceEnum, MovementRule>
) {
    fun getRule(pieceEnum: PieceEnum): MovementRule {
        return this.rulesMap[pieceEnum]!!
    }
}