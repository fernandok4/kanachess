package br.com.kanasha.chess.engine.process.simulate

import br.com.kanasha.chess.engine.process.AvailablePieceCoordinatesProcess
import br.com.kanasha.chess.engine.process.ChessProcess
import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.piece.IPiece

class SimulateMovementToDefendKing(private val board: IBoard,
                                   private val pieceToDefend: IPiece,
                                   private val possibleCoordanate: Pair<Int, Int>): ChessProcess {
    override fun execute(): Boolean {
        val initialCoordanate = board.getPieceCoordenate(pieceToDefend)
        board.squares[initialCoordanate.first][initialCoordanate.second].piece = null
        board.squares[possibleCoordanate.first][possibleCoordanate.second].piece = pieceToDefend
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        board.squares[possibleCoordanate.first][possibleCoordanate.second].piece = null
        board.squares[initialCoordanate.first][initialCoordanate.second].piece = pieceToDefend
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        return true
    }
}