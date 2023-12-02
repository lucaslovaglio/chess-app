package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.PieceEnum
import edu.austral.dissis.checkers.validator.piece.*
import edu.austral.dissis.common.factory.RulesFactory
import edu.austral.dissis.common.game.RulesMap

object ClassicRules: RulesFactory {
    override fun createRulesMap(): RulesMap {
        return RulesMap(
            mapOf(
                PieceEnum.PAWN to PawnMovement(),
                PieceEnum.KING to KingMovement()
            )
        )
    }
}