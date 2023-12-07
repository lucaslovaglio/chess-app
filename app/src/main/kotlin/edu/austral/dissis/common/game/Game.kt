package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.ResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.MovementResult
import edu.austral.dissis.common.piece.ColorEnum
import edu.austral.dissis.common.validator.ValidatorResult
import edu.austral.dissis.common.validator.composite.AndValidator

class Game(
    val board: Board,
    val validator: Validator,
    val specialRule: Validator,
    val winConditionValidator: Validator,
    val rulesMap: RulesMap,
    val turnManager: TurnManager
) {

//    fun validate(movementData: MovementData): ValidatorResult {
//        var result = ValidatorResult(ValidatorResultEnum.PASSED)
//
//        for (validator in validator) {
//            val validatorResult = validator.validate(movementData, this)
//            result = validatorResult.addSpecialAction(result.getSpecialActions())
//            if (!validatorResult.isPassed()) {
//                return result // Si un validador falla, el resultado es fallido
//            }
//        }
//
//        return result
//    }

    private fun validate(movementData: MovementData): ValidatorResult {
        return AndValidator(listOf(validator, specialRule)).validate(movementData, this)
    }

    fun getCurrentPlayer(): Player {
        return turnManager.getCurrentPlayer()
    }

    fun move(movementData: MovementData): MovementResult {
//        val validatorsResult: ValidatorResult = validate(movementData)
        val validatorResult: ValidatorResult = validate(movementData)

        if (!validatorResult.isPassed())
            return MovementResult(ResultEnum.INVALID_MOVEMENT, validatorResult.getResultMessage(), this)

        val board = board.move(movementData)
        val newBoard = validatorResult.executeSpecialActions(board, validatorResult.getSpecialActions())
        val newGame = turnManager.nextTurn(movementData, this, newBoard)

        // si cambio el turno valido las game over sino no
        if (hasChangedTurn(this, newGame)) {
            val gameOverCheck: ValidatorResult = winConditionValidator.validate(movementData, newGame)
            if (gameOverCheck.isPassed())
                return MovementResult(ResultEnum.GAME_OVER, validatorResult.getResultMessage(), this)
        }
        return MovementResult(ResultEnum.VALID_MOVEMENT, validatorResult.getResultMessage(), newGame)
    }

    fun getEnemyTeam(color: ColorEnum): ColorEnum {
        return if (color == ColorEnum.WHITE) ColorEnum.BLACK else ColorEnum.WHITE
    }

    private fun hasChangedTurn(game: Game, newGame: Game): Boolean {
        return game.getCurrentPlayer() != newGame.getCurrentPlayer()
    }

}