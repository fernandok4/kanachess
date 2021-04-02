package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPiece

class AvailablePieceCoordinatesProcess(private val board: IBoard,
                                       private val pieces: List<IPiece>) : ChessProcess {

    override fun execute(): Boolean {
        pieces.filter { !it.isDead }.sortedBy { if (it.color == board.colorRound) 1 else 0 }.forEach {
            val allowedCoordinates = mutableListOf<MoveNotation>()
            for(moveType in it.pieceMovementTypes){
                allowedCoordinates.addAll(moveType.calculateAllowedCoordinates(board))
            }
            it.allowedMoves = allowedCoordinates
        }
        return true
    }
}