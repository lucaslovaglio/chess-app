package edu.austral.dissis.server


import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.adapter.adaptColor
import edu.austral.dissis.common.adapter.adaptMove
import edu.austral.dissis.common.adapter.getCurrentPlayer
import edu.austral.dissis.common.adapter.getPieces
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.movement.MovementResult
import edu.austral.dissis.common.movement.ResultEnum
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

class GameServer(
    private var game: Game,
    private val serverBuilder: ServerBuilder = NettyServerBuilder.createDefault()
) {

    private val server: Server
    init {
        server = buildServer()
        server.start()
    }

    fun handleMove(move: Move){

        val movementData = adaptMove(game, move)
        val result: MovementResult = game.move(movementData)
        return when(result.result) {
            ResultEnum.VALID_MOVEMENT ->
                server.broadcast(Message("new-game-state",handleNewGameState(result)))
            ResultEnum.INVALID_MOVEMENT ->
                server.broadcast(Message("invalid-move",InvalidMove(result.message)))
            ResultEnum.GAME_OVER ->
                server.broadcast(Message("game-over",GameOver(getCurrentPlayer(game))))
        }
    }

    fun stop(){
        server.stop()
    }

    fun handleInitialConnection(){
        val boardSize = BoardSize(game.board.height, game.board.width)
        val pieces = getPieces(game)
        val color = adaptColor(game.getCurrentPlayer().color)
        server.broadcast(Message("initial-state",InitialState(boardSize,pieces,color)))
    }

    private fun handleNewGameState(result: MovementResult): MoveResult{
        game = result.newGame
        val pieces = getPieces(game)
        val color = adaptColor(game.getCurrentPlayer().color)
        return NewGameState(pieces,color)
    }

    private fun buildServer(): Server{
        return serverBuilder
            .withPort(8080)
            .addMessageListener("initial-state",
                object : TypeReference<Message<Unit>>() {},
                InitListener(this))
            .addMessageListener(
                "move",
                object : TypeReference<Message<Move>> () {},
                MoveMessageListener(this)
            )
            .build()
    }
}