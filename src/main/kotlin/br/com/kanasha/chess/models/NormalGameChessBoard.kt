package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.*

class NormalGameChessBoard: Board() {

    fun resetBoardPieces(){
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