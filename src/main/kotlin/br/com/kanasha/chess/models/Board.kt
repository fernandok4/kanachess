package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.IPiece

open class Board {

    var colorRound = ColorPiece.WHITE
    var squares = Array(8, { Array(8, { Square() }) })

    val allPieces by lazy {
        squares.fold(mutableListOf<IPiece>()){ acc, square ->
            square.forEach {
                if(it.piece != null){
                    acc.add(it.piece!!)
                }
            }
            acc
        }
    }
    val piecesGroupedByColor by lazy {
        allPieces.groupBy { it.color }
    }

    fun getSquare(x: Int, y: Int) = squares[x][y]

    fun getSquarePiece(x: Int, y: Int) = squares[x][y].piece
}