package edu.austral.dissis.common.validator.movement

import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.ValidatorResultEnum


class OnlyDirectionMovement(private val isForward: Boolean): Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val piece = movementData.piece
        val team = piece?.color
        when(team){
            ColorEnum.WHITE -> {
                return if(isForward){
                    if (movementData.squareFrom.y < movementData.squareTo.y) ValidatorResult(ValidatorResultEnum.PASSED)
                    else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
                } else {
                    if (movementData.squareFrom.y > movementData.squareTo.y) ValidatorResult(ValidatorResultEnum.PASSED)
                    else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
                }
            }
            ColorEnum.BLACK -> {
                return if(isForward){
                    if (movementData.squareFrom.y > movementData.squareTo.y) ValidatorResult(ValidatorResultEnum.PASSED)
                    else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
                } else {
                    if (movementData.squareFrom.y < movementData.squareTo.y) ValidatorResult(ValidatorResultEnum.PASSED)
                    else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)
                }
            }
            else -> return ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)

        }
    }
}