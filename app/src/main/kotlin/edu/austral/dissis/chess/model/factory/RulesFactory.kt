package edu.austral.dissis.chess.model.factory

import edu.austral.dissis.chess.interfaces.Validator
import edu.austral.dissis.chess.model.RulesMap

interface RulesFactory {
    fun createRulesMap(): RulesMap
}