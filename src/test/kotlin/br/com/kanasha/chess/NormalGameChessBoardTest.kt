package br.com.kanasha.chess

import br.com.kanasha.chess.models.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.Pawn
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NormalGameChessBoardTest {

    @Test
    fun verifyResetBoardPiecesIfPiecesIsOnInitialPositions(){
        val board = NormalGameChessBoard()

        board.resetBoard()

        for(xValue in board.squares){
            for((yIndex, yValue) in xValue.withIndex()){
                if(yIndex <= 1 || yIndex >= 6){
                    Assertions.assertTrue(yValue.piece != null)
                } else {
                    Assertions.assertTrue(yValue.piece == null)
                }
            }
        }
    }

    @Test
    fun verifyResetBoardPiecesGameAlreadyBeganIfPiecesIsOnInitialPositions(){
        val board = NormalGameChessBoard()
        // e4
        board.squares[6][4].piece = null
        board.squares[4][4].piece = Pawn(ColorPiece.WHITE)
        board.resetBoard()

        for(xValue in board.squares){
            for((yIndex, yValue) in xValue.withIndex()){
                if(yIndex <= 1 || yIndex >= 6){
                    Assertions.assertTrue(yValue.piece != null)
                } else {
                    Assertions.assertTrue(yValue.piece == null)
                }
            }
        }
    }
}