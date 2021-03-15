package br.com.kanasha.chess.engine

import br.com.kanasha.chess.engine.process.FilterMovesKingUnderAttackProcess
import br.com.kanasha.chess.engine.process.MoveNotationsProcess
import br.com.kanasha.chess.engine.process.AvailablePieceCoordinatesProcess
import br.com.kanasha.chess.engine.process.ResetPiecesStatusProcess
import br.com.kanasha.chess.models.Board

class ProcessNormalChessBoardEngine(private val board: Board) {

    fun process(){
        ResetPiecesStatusProcess(board).execute()
        AvailablePieceCoordinatesProcess(board).execute()
        FilterMovesKingUnderAttackProcess(board).execute()
        MoveNotationsProcess(board).execute()
    }
}