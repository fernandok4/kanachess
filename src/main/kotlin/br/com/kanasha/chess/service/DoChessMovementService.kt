package br.com.kanasha.chess.service

import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import br.com.kanasha.chess.models.board.IBoard

class DoChessMovementService(private val board: IBoard, private val moveNotation: String) {

    fun execute(){
        ProcessNormalChessBoardEngine(board).process()
        val piece = board.piecesGroupedByColor[board.colorRound]!!.find {
            it.allowedMoves.any {
                it.notation == moveNotation
            }
        } ?: throw Exception()
        val movement = piece.allowedMoves.find { it.notation == moveNotation } ?: throw Exception()
        val movementType = piece.pieceMovementTypes.find { it::class == movement.kClass } ?:
                           throw Exception()
        movementType.doMovement(board, moveNotation)
        board.nextTurn()
        ProcessNormalChessBoardEngine(board).process()
    }
}