package edu.austral.dissis.common.validator

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.specialAction.SpecialAction

class ValidatorResult(
    private val result: ValidatorResultEnum,
    private val specialActions: List<SpecialAction> = emptyList()
) {
    fun isPassed(): Boolean {
        return result == ValidatorResultEnum.PASSED
    }

    fun executeSpecialActions(board: Board, specialActions: List<SpecialAction>): Board {
        var currentBoard = board
        specialActions.forEach { currentBoard = it.execute(currentBoard) }
        return currentBoard
    }


    fun addSpecialAction(action: List<SpecialAction>): ValidatorResult {
        return ValidatorResult(result, specialActions + action)
    }

    fun getSpecialActions(): List<SpecialAction> {
        return specialActions
    }

    fun getResult(): ValidatorResultEnum {
        return result
    }

    fun getResultMessage(): String {
        return result.message
    }
}