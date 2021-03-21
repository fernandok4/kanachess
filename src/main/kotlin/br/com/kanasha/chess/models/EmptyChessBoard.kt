package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.*

class EmptyChessBoard: IBoard {
    override var colorRound = ColorPiece.WHITE
    override var squares = Array(8, { Array(8, { Square() }) })

    override val allPieces by lazy {
        squares.fold(mutableListOf<IPiece>()){ acc, square ->
            square.forEach {
                if(it.piece != null){
                    acc.add(it.piece!!)
                }
            }
            acc
        }
    }
    override val piecesGroupedByColor by lazy {
        allPieces.groupBy { it.color }
    }

    override fun resetBoard(){
        squares = Array(8, { Array(8, { Square() }) })
    }
}