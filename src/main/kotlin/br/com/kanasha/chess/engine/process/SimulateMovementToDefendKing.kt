package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.piece.IPiece

class SimulateMovementToDefendKing(private val board: Board,
                                   private val pieceToDefend: IPiece,
                                   private val possibleCoordanate: Pair<Int, Int>): ChessProcess {
    override fun execute(): Boolean {
        val initialCoordanate = board.getCoordenate(pieceToDefend)
        board.squares[initialCoordanate.first][initialCoordanate.second].piece = null
        board.squares[possibleCoordanate.first][possibleCoordanate.second].piece = pieceToDefend
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        board.squares[possibleCoordanate.first][possibleCoordanate.second].piece = null
        board.squares[initialCoordanate.first][initialCoordanate.second].piece = pieceToDefend
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        return true
    }
}