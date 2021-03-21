package br.com.kanasha.chess.engine.process

import br.com.kanasha.chess.models.IBoard

class ResetPiecesStatusProcess(private val board: IBoard): ChessProcess {
    override fun execute(): Boolean{
        board.squares.forEach {
            it.forEach { square ->
                square.piece?.resetPieceStatus()
            }
        }
        return true
    }
}