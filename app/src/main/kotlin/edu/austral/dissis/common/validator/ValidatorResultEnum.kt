package edu.austral.dissis.common.validator

enum class ValidatorResultEnum(val message: String) {
    PASSED("Passed"),
    INVALID_TEAM("You can't move your opponent's pieces"),
    INVALID_POSITION("You can't move to that position"),
    INVALID_MOVEMENT("You can't move like that"),
    INVALID_CAPTURE("You can't capture that piece"),
    INVALID_SQUARE("You can't move an empty square"),
}