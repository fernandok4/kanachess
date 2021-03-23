package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN

class ParseMoveNotationsProcess(private val board: IBoard): ChessProcess {
    override fun execute(): Boolean {
        board.allPieces.filter { !it.isDead }.forEach { piece ->
            piece.allowedMoves.forEach {
                it.moveNotation = it.coordanate.toNotationPGN(board, piece)
            }
        }
        return true
    }
}