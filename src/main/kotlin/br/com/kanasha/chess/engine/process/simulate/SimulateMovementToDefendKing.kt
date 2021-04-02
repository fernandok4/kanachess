package br.com.kanasha.chess.engine.process.simulate

import br.com.kanasha.chess.engine.process.AvailablePieceCoordinatesProcess
import br.com.kanasha.chess.engine.process.ChessProcess
import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.piece.IPiece

class SimulateMovementToDefendKing(private val board: IBoard,
                                   private val pieceToDefend: IPiece,
                                   private val possibleCoordanate: SquareCoordinate
): ChessProcess {
    override fun execute(): Boolean {
        val initialCoordanate = board.getPieceCoordinate(pieceToDefend)
        board.squares[initialCoordanate.x][initialCoordanate.y].piece = null
        board.squares[possibleCoordanate.x][possibleCoordanate.y].piece = pieceToDefend
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        board.squares[possibleCoordanate.x][possibleCoordanate.y].piece = null
        board.squares[initialCoordanate.x][initialCoordanate.y].piece = pieceToDefend
        AvailablePieceCoordinatesProcess(board, board.allPieces).execute()
        return true
    }
}