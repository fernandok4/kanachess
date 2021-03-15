package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.IPiece

class SimulateMovementToDefendKing(private val board: Board,
                                   private val piece: IPiece,
                                   private val possibleCoordanate: Pair<Int, Int>): ChessProcess {
    override fun execute(): Boolean {
        val initialCoordanate = piece.getCoordenate()
        board.squares[initialCoordanate.first][initialCoordanate.second].piece = null
        board.squares[initialCoordanate.first][initialCoordanate.second].piece = piece
        return true
    }
}