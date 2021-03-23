package br.com.kanasha.chess.models.board

import br.com.kanasha.chess.models.board.square.Square
import br.com.kanasha.chess.models.piece.*

class NormalGameChessBoard: IBoard {

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
        // BLACK PIECES INITIAL SQUARES
        squares[0][7].piece = Rook(ColorPiece.BLACK)
        squares[7][7].piece = Rook(ColorPiece.BLACK)
        squares[1][7].piece = Knight(ColorPiece.BLACK)
        squares[6][7].piece = Knight(ColorPiece.BLACK)
        squares[2][7].piece = Bishop(ColorPiece.BLACK)
        squares[5][7].piece = Bishop(ColorPiece.BLACK)
        squares[3][7].piece = Queen(ColorPiece.BLACK)
        squares[4][7].piece = King(ColorPiece.BLACK)
        squares[0][6].piece = Pawn(ColorPiece.BLACK)
        squares[7][6].piece = Pawn(ColorPiece.BLACK)
        squares[1][6].piece = Pawn(ColorPiece.BLACK)
        squares[6][6].piece = Pawn(ColorPiece.BLACK)
        squares[2][6].piece = Pawn(ColorPiece.BLACK)
        squares[5][6].piece = Pawn(ColorPiece.BLACK)
        squares[3][6].piece = Pawn(ColorPiece.BLACK)
        squares[4][6].piece = Pawn(ColorPiece.BLACK)

        // WHITE PIECES INITIAL SQUARES
        squares[0][0].piece = Rook(ColorPiece.WHITE)
        squares[7][0].piece = Rook(ColorPiece.WHITE)
        squares[1][0].piece = Knight(ColorPiece.WHITE)
        squares[6][0].piece = Knight(ColorPiece.WHITE)
        squares[2][0].piece = Bishop(ColorPiece.WHITE)
        squares[5][0].piece = Bishop(ColorPiece.WHITE)
        squares[3][0].piece = Queen(ColorPiece.WHITE)
        squares[4][0].piece = King(ColorPiece.WHITE)
        squares[0][1].piece = Pawn(ColorPiece.WHITE)
        squares[7][1].piece = Pawn(ColorPiece.WHITE)
        squares[1][1].piece = Pawn(ColorPiece.WHITE)
        squares[6][1].piece = Pawn(ColorPiece.WHITE)
        squares[2][1].piece = Pawn(ColorPiece.WHITE)
        squares[5][1].piece = Pawn(ColorPiece.WHITE)
        squares[3][1].piece = Pawn(ColorPiece.WHITE)
        squares[4][1].piece = Pawn(ColorPiece.WHITE)
    }
}