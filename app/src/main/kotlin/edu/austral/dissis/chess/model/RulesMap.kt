package edu.austral.dissis.chess.model

import edu.austral.dissis.chess.enums.PieceEnum
import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.movements.pieceMovement.*
import java.util.*
import kotlin.collections.HashMap

class RulesMap(
    private val rulesMap: Map<PieceEnum, MovementRule>
) {
    fun getRule(pieceEnum: PieceEnum): MovementRule {
        return this.rulesMap[pieceEnum]!!
    }
}