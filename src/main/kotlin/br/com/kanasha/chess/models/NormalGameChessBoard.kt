package br.com.kanasha.chess.models

import br.com.kanasha.chess.models.board.Square
import br.com.kanasha.chess.models.piece.*

class NormalGameChessBoard: Board() {

    fun resetBoardPieces(){
        squares = Array(8, { Array(8, { Square() }) })
        // BLACK PIECES INITIAL SQUARES
        squares[0][7].piece = Rook(ColorEnum.BLACK, "a8")
        squares[7][7].piece = Rook(ColorEnum.BLACK, "h8")
        squares[1][7].piece = Knight(ColorEnum.BLACK, "b8")
        squares[6][7].piece = Knight(ColorEnum.BLACK, "g8")
        squares[2][7].piece = Bishop(ColorEnum.BLACK, "c8")
        squares[5][7].piece = Bishop(ColorEnum.BLACK, "f8")
        squares[3][7].piece = Queen(ColorEnum.BLACK, "d8")
        squares[4][7].piece = King(ColorEnum.BLACK, "e8")
        squares[0][6].piece = Pawn(ColorEnum.BLACK, "a7")
        squares[7][6].piece = Pawn(ColorEnum.BLACK, "h7")
        squares[1][6].piece = Pawn(ColorEnum.BLACK, "b7")
        squares[6][6].piece = Pawn(ColorEnum.BLACK, "g7")
        squares[2][6].piece = Pawn(ColorEnum.BLACK, "c7")
        squares[5][6].piece = Pawn(ColorEnum.BLACK, "f7")
        squares[3][6].piece = Pawn(ColorEnum.BLACK, "d7")
        squares[4][6].piece = Pawn(ColorEnum.BLACK, "e7")

        // WHITE PIECES INITIAL SQUARES
        squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        squares[7][0].piece = Rook(ColorEnum.WHITE, "h1")
        squares[1][0].piece = Knight(ColorEnum.WHITE, "b1")
        squares[6][0].piece = Knight(ColorEnum.WHITE, "g1")
        squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        squares[5][0].piece = Bishop(ColorEnum.WHITE, "f1")
        squares[3][0].piece = Queen(ColorEnum.WHITE, "d1")
        squares[4][0].piece = King(ColorEnum.WHITE, "e1")
        squares[0][1].piece = Pawn(ColorEnum.WHITE, "a2")
        squares[7][1].piece = Pawn(ColorEnum.WHITE, "h2")
        squares[1][1].piece = Pawn(ColorEnum.WHITE, "b2")
        squares[6][1].piece = Pawn(ColorEnum.WHITE, "g2")
        squares[2][1].piece = Pawn(ColorEnum.WHITE, "c2")
        squares[5][1].piece = Pawn(ColorEnum.WHITE, "f2")
        squares[3][1].piece = Pawn(ColorEnum.WHITE, "d2")
        squares[4][1].piece = Pawn(ColorEnum.WHITE, "e2")
    }
}