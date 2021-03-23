package br.com.kanasha.chess.engine

import br.com.kanasha.chess.engine.process.FilterMovesKingUnderAttackProcess
import br.com.kanasha.chess.engine.process.ParseMoveNotationsProcess
import br.com.kanasha.chess.engine.process.AvailablePieceCoordinatesProcess
import br.com.kanasha.chess.engine.process.ResetPiecesStatusProcess
import br.com.kanasha.chess.models.board.IBoard

class ProcessNormalChessBoardEngine(private val board: IBoard) {

    fun process(){
        ResetPiecesStatusProcess(board).execute()
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        FilterMovesKingUnderAttackProcess(board).execute()
        ParseMoveNotationsProcess(board).execute()
    }
}