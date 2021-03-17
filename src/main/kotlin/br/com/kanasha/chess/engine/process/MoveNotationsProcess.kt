package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN

class MoveNotationsProcess(private val board: Board): ChessProcess {
    override fun execute(): Boolean {
        board.allPieces.filter { !it.isDead }.forEach { piece ->
            piece.allowedMoves.forEach {
                it.moveNotation = it.coordanate.toNotationPGN(board, piece)
            }
        }
        return true
    }
}