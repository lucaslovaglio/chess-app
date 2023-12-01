package edu.austral.dissis.common.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.validator.MovementRule


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