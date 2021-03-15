package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.Board

class ResetPiecesStatusProcess(private val board: Board): ChessProcess {
    override fun execute(): Boolean{
        board.squares.forEach {
            it.forEach { square ->
                square.piece?.resetPieceStatus()
            }
        }
        return true
    }
}