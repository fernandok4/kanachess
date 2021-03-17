package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.*

class NormalGameChessBoard: Board() {

    fun resetBoardPieces(){
        squares = Array(8, { Array(8, { Square() }) })
        // BLACK PIECES INITIAL SQUARES
        squares[0][7].piece = Rook(ColorPiece.BLACK, "a8")
        squares[7][7].piece = Rook(ColorPiece.BLACK, "h8")
        squares[1][7].piece = Knight(ColorPiece.BLACK, "b8")
        squares[6][7].piece = Knight(ColorPiece.BLACK, "g8")
        squares[2][7].piece = Bishop(ColorPiece.BLACK, "c8")
        squares[5][7].piece = Bishop(ColorPiece.BLACK, "f8")
        squares[3][7].piece = Queen(ColorPiece.BLACK, "d8")
        squares[4][7].piece = King(ColorPiece.BLACK, "e8")
        squares[0][6].piece = Pawn(ColorPiece.BLACK, "a7")
        squares[7][6].piece = Pawn(ColorPiece.BLACK, "h7")
        squares[1][6].piece = Pawn(ColorPiece.BLACK, "b7")
        squares[6][6].piece = Pawn(ColorPiece.BLACK, "g7")
        squares[2][6].piece = Pawn(ColorPiece.BLACK, "c7")
        squares[5][6].piece = Pawn(ColorPiece.BLACK, "f7")
        squares[3][6].piece = Pawn(ColorPiece.BLACK, "d7")
        squares[4][6].piece = Pawn(ColorPiece.BLACK, "e7")

        // WHITE PIECES INITIAL SQUARES
        squares[0][0].piece = Rook(ColorPiece.WHITE, "a1")
        squares[7][0].piece = Rook(ColorPiece.WHITE, "h1")
        squares[1][0].piece = Knight(ColorPiece.WHITE, "b1")
        squares[6][0].piece = Knight(ColorPiece.WHITE, "g1")
        squares[2][0].piece = Bishop(ColorPiece.WHITE, "c1")
        squares[5][0].piece = Bishop(ColorPiece.WHITE, "f1")
        squares[3][0].piece = Queen(ColorPiece.WHITE, "d1")
        squares[4][0].piece = King(ColorPiece.WHITE, "e1")
        squares[0][1].piece = Pawn(ColorPiece.WHITE, "a2")
        squares[7][1].piece = Pawn(ColorPiece.WHITE, "h2")
        squares[1][1].piece = Pawn(ColorPiece.WHITE, "b2")
        squares[6][1].piece = Pawn(ColorPiece.WHITE, "g2")
        squares[2][1].piece = Pawn(ColorPiece.WHITE, "c2")
        squares[5][1].piece = Pawn(ColorPiece.WHITE, "f2")
        squares[3][1].piece = Pawn(ColorPiece.WHITE, "d2")
        squares[4][1].piece = Pawn(ColorPiece.WHITE, "e2")
    }
}