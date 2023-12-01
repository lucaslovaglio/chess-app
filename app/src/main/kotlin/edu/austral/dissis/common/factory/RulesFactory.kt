package edu.austral.dissis.common.factory

import edu.austral.dissis.common.game.RulesMap

interface RulesFactory {
    fun createRulesMap(): RulesMap
}