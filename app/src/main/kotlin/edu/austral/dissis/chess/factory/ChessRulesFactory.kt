package edu.austral.dissis.chess.factory

import edu.austral.dissis.common.factory.RulesFactory
import edu.austral.dissis.chess.validator.piece.*
import edu.austral.dissis.chess.piece.PieceEnum
import edu.austral.dissis.common.game.RulesMap


object ClassicRules: RulesFactory {
    override fun createRulesMap(): RulesMap {
        return RulesMap(
            mapOf(
                PieceEnum.PAWN to PawnMovement(),
                PieceEnum.ROOK to RookMovement(),
                PieceEnum.KNIGHT to KnightMovement(),
                PieceEnum.BISHOP to BishopMovement(),
                PieceEnum.QUEEN to QueenMovement(),
                PieceEnum.KING to KingMovement()
            )
        )
    }
}