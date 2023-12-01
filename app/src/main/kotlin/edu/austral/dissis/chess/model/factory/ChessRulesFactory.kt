package edu.austral.dissis.chess.model.factory

import edu.austral.dissis.chess.enums.PieceEnum
import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.RulesMap
import edu.austral.dissis.chess.model.movements.pieceMovement.*
import edu.austral.dissis.chess.model.validator.PositionValidator
import edu.austral.dissis.chess.model.validator.TeamValidator

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