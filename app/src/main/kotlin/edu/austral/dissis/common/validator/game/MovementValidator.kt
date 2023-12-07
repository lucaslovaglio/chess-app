package edu.austral.dissis.common.validator.game

import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.validator.ValidatorResult

class MovementValidator: Validator {
    override fun validate(movementData: MovementData, game: Game): ValidatorResult {
        val piece = movementData.piece

        val rules = game.rulesMap
        val rule = rules.getRule(piece!!.name)
        return rule.validate(movementData, game)
//        return if (rule.validate(movementData, game).isPassed())
//                    ValidatorResult(ValidatorResultEnum.PASSED)
//                else ValidatorResult(ValidatorResultEnum.INVALID_MOVEMENT)


    }
}