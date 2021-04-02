package br.com.kanasha.chess.models.board

import br.com.kanasha.chess.models.board.square.Square
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.IPiece

interface IBoard {
    val size: Int
    val allPieces: MutableList<IPiece>
    val piecesGroupedByColor: Map<ColorPiece, List<IPiece>>
    var colorRound: ColorPiece
    var squares: Array<Array<Square>>

    fun resetBoard()
    fun getSquare(x: Int, y: Int): Square = squares[x][y]
    fun getPieceCoordinate(piece: IPiece): SquareCoordinate {
        var yCoordinate = -1
        var xCoordinate = -1
        for((xIndex, squares) in this.squares.withIndex()){
            yCoordinate = squares.indexOfFirst { it.piece == piece }
            if(yCoordinate != -1){
                xCoordinate = xIndex
                break
            }
        }
        return SquareCoordinate(xCoordinate, yCoordinate)
    }
    fun nextTurn()
}