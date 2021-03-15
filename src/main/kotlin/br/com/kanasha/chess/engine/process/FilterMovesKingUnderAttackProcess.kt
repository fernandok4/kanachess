package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.piece.King

class FilterMovesKingUnderAttackProcess(private val board: Board): ChessProcess {
    override fun execute(): Boolean {
        val allyPieces = board.piecesGroupedByColor[board.colorRound]!!
        val king = allyPieces.find { it is King } ?: return false
        if(!king.isUnderAttack) return true
        allyPieces.forEach { piece ->
            piece.allowedCoordinates = piece.allowedCoordinates.filter { SimulateMovementToDefendKing(board, piece, it).execute() }
        }
        return true
    }
}