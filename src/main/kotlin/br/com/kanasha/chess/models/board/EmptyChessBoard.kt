package br.com.kanasha.chess.models.board

import br.com.kanasha.chess.models.board.square.Square
import br.com.kanasha.chess.models.piece.*

class EmptyChessBoard: IBoard {
    override val size: Int = 8
    override var colorRound = ColorPiece.WHITE
    override var squares = Array(size, { Array(size, { Square() }) })

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

    override fun nextTurn(){
        squares.forEach {
            it.forEach {
                it.isUnderEnemyAttack = false
            }
        }
        colorRound = if(colorRound == ColorPiece.WHITE) ColorPiece.BLACK else ColorPiece.WHITE
    }
}