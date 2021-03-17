package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.King

class AvailablePieceCoordinatesProcess(private val board: Board) : ChessProcess {

    override fun execute(): Boolean {
        board.allPieces.filter { !it.isDead }.sortedBy { if (it.color == board.colorRound) 1 else 0 }.forEach {
            val allowedCoordinates = mutableListOf<Pair<Int, Int>>()
            for(moveType in it.pieceMovementTypes){
                allowedCoordinates.addAll(moveType.calculateAllowedCoordinates(board))
            }
            it.allowedMoves = allowedCoordinates.map { SquareCoordanate(it) }
        }
        return true
    }
}