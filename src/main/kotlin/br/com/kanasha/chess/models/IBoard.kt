package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.IPiece

interface IBoard {
    var colorRound: ColorPiece
    var squares: Array<Array<Square>>
    val allPieces: MutableList<IPiece>
    val piecesGroupedByColor: Map<ColorPiece, List<IPiece>>

    fun resetBoard()
    fun getSquare(x: Int, y: Int): Square = squares[x][y]
    fun getPieceCoordenate(piece: IPiece): Pair<Int, Int> {
        var yCoordinate = -1
        var xCoordinate = -1
        for((xIndex, squares) in this.squares.withIndex()){
            yCoordinate = squares.indexOfFirst { it.piece == piece }
            if(yCoordinate != -1){
                xCoordinate = xIndex
                break
            }
        }
        return Pair(xCoordinate, yCoordinate)
    }
}