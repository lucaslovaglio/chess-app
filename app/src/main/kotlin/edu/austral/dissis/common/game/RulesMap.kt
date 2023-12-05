package edu.austral.dissis.common.game

import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.validator.MovementRule
import edu.austral.dissis.common.validator.Validator

class RulesMap(
    private val rulesMap: Map<PieceType, Validator>
) {
    fun getRule(pieceType: PieceType): Validator {
        return this.rulesMap[pieceType]!!
    }
}