package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.movement.ResultEnum
import edu.austral.dissis.common.validator.ValidatorResultEnum
import edu.austral.dissis.common.validator.Validator
import edu.austral.dissis.common.movement.MovementData
import edu.austral.dissis.common.movement.MovementResult
import edu.austral.dissis.common.validator.ValidatorResult

class Game(
    val board: Board,
    val validators: List<Validator>,
    val winConditionValidator: Validator,
    val rulesMap: RulesMap,
    private val turnManager: TurnManager
) {


//    constructor(
//        players: List<Player>,
//        currentPlayer: Player,
//        startingBoard: StartingBoard,
//        validators: List<Validator>,
//        winConditionValidator: WinConditionValidator
//    ) : this(players, currentPlayer, startingBoard, startingBoard.generateBoard(), validators, winConditionValidator) {
//        if (players.size < 2) {
//            throw Exception("There must be at least two players")
//        }
//    }

//    constructor()

//    fun validate(movementData: MovementData): ValidatorResultEnum {
//        turnValidator.validate(movementData) // Validación específica de turno
//        movementValidator.validate(movementData) // Validación específica de movimiento
//        for (validator in validators) {
//            val validatorResult = validator.validate(movementData)
//            if (validatorResult != ValidatorResultEnum.PASSED) {
//                return validatorResult // Si un validador falla, el resultado es fallido
//            }
//        }
//        return ValidatorResultEnum.PASSED
//    }

//    fun move(movementData: MovementData): Game {
//        val newBoard = board.move(movementData)
//        return Game(
//            players,
//            nextPlayer(),
//            startingBoard,
//            newBoard,
//            validators,
//            winConditionValidator
//        )
//    }

    fun validate(movementData: MovementData): ValidatorResult {
        var result = ValidatorResult(ValidatorResultEnum.PASSED)

        for (validator in validators) {
            val validatorResult = validator.validate(movementData, this)
            result = validatorResult.addSpecialAction(result.getSpecialActions())
            if (!validatorResult.isPassed()) {
                return result // Si un validador falla, el resultado es fallido
            }
        }

        return result
    }

    fun getCurrentPlayer(): Player {
        return turnManager.getCurrentPlayer()
    }

    fun move(movementData: MovementData): MovementResult {
        val validatorsResult: ValidatorResult = validate(movementData)

        if (!validatorsResult.isPassed())
            return MovementResult(ResultEnum.INVALID_MOVEMENT, validatorsResult.getResultMessage(), this)

        val board = board.move(movementData)
        val newBoard = validatorsResult.executeSpecialActions(board, validatorsResult.getSpecialActions())
        val newGame = turnManager.nextTurn(movementData, this, newBoard)

        val gameOverCheck: ValidatorResult = winConditionValidator.validate(movementData, newGame)
        if (gameOverCheck.isPassed())
            return MovementResult(ResultEnum.GAME_OVER, validatorsResult.getResultMessage(), this)
        return MovementResult(ResultEnum.VALID_MOVEMENT, validatorsResult.getResultMessage(), newGame)
    }

}