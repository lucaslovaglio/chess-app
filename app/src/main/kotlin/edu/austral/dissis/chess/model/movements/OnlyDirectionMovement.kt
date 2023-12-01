package edu.austral.dissis.chess.model.movements

import edu.austral.dissis.chess.enums.ColorEnum
import edu.austral.dissis.chess.interfaces.MovementRule
import edu.austral.dissis.chess.model.Game
import edu.austral.dissis.chess.model.MovementData

class OnlyDirectionMovement(private val isForward: Boolean): MovementRule {
    override fun validate(movementData: MovementData, game: Game): Boolean {
        val piece = movementData.piece
        val team = piece?.color
        when(team){
            ColorEnum.WHITE -> {
                return if(isForward){
                    movementData.squareFrom.y < movementData.squareTo.y
                } else {
                    movementData.squareFrom.y > movementData.squareTo.y
                }
            }
            ColorEnum.BLACK -> {
                return if(isForward){
                    movementData.squareFrom.y > movementData.squareTo.y
                } else {
                    movementData.squareFrom.y < movementData.squareTo.y
                }
            }
            else -> return false

        }
    }
}