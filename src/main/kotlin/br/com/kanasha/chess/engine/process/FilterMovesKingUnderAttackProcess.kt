package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.engine.process.simulate.SimulateMovementToDefendKing
import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.piece.King

class FilterMovesKingUnderAttackProcess(private val board: IBoard): ChessProcess {
    override fun execute(): Boolean {
        val allyPieces = board.piecesGroupedByColor[board.colorRound]!!
        val king = allyPieces.find { it is King } ?: return false
        if(!king.isUnderAttack) return true
        allyPieces.forEach { piece ->
            piece.allowedMoves = piece.allowedMoves.filter {
                SimulateMovementToDefendKing(board, piece, it.coordinate).execute()
            }
        }
        return true
    }
}