package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.ColorEnum
import br.com.kanasha.chess.models.piece.IPiece

open class Board {

    var squares = Array(8, { Array(8, { Square() }) })
    val piecesGroupedByColor by lazy {
        squares.fold(mutableListOf<IPiece>()){ acc, square ->
            square.forEach {
                if(it.piece != null){
                    acc.add(it.piece!!)
                }
            }
            acc
        }.groupBy { it.color }
    }

    fun getSquarePiece(x: Int, y: Int) = squares[x][y].piece
}