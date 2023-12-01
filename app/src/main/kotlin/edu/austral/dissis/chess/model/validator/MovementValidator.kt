package edu.austral.dissis.chess.model.validator

import edu.austral.dissis.chess.enums.ValidatorResultEnum
import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.Board
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData
import edu.austral.dissis.chess.model.Square

class MovementValidator(): Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResultEnum {
        val piece = movementData.piece
//        val xFrom = movementData.squareFrom.x
//        val yFrom = movementData.squareFrom.y
//        val xTo = movementData.squareTo.x
//        val yTo = movementData.squareTo.y

        val rules = game.rulesMap
        val rule = rules.getRule(piece!!.name)
        return if (rule.validate(movementData, game)) ValidatorResultEnum.PASSED else ValidatorResultEnum.INVALID_MOVEMENT


//        for (i in xFrom..xTo) {
//            for (j in yFrom..yTo) {
//                if (piece.canJump && board.getPieceAt(i, j) != null) continue
//                if (i == xFrom && j == yFrom) continue
//                if (i == xTo && j == yTo) continue
//                if (piece.canMoveTo(MovementData(movementData.player, piece, Square(piece, i, j), Square(piece, i+1, j+1)))) continue //este esta mal, no funciona para el caballo
//                return ValidatorResultEnum.INVALID_MOVEMENT
//            }
//        }
//        return ValidatorResultEnum.PASSED
    }
}