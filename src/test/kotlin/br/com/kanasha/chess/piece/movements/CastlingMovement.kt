package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import br.com.kanasha.chess.models.board.EmptyChessBoard
import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.models.board.square.Square
import br.com.kanasha.chess.models.piece.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CastlingMovement {

    @Test
    fun testKingCastlingMovementOnEmptyBoardToTwoSides(){
        val board = EmptyChessBoard()
        board.resetBoard()
        board.squares[4][0] = Square(King(ColorPiece.WHITE))
        board.squares[0][0] = Square(Rook(ColorPiece.WHITE))
        board.squares[7][0] = Square(Rook(ColorPiece.WHITE))
        val piece = board.squares[4][0].piece!!
        val expectedMoveNotations = listOf("O-O-O", "O-O")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.notation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }

    @Test
    fun testKingBigCastling(){
        val board = EmptyChessBoard()
        board.resetBoard()
        board.squares[4][0] = Square(King(ColorPiece.WHITE))
        board.squares[5][0] = Square(Bishop(ColorPiece.WHITE))
        board.squares[0][0] = Square(Rook(ColorPiece.WHITE))
        board.squares[7][0] = Square(Rook(ColorPiece.WHITE))
        val piece = board.squares[4][0].piece!!
        val expectedMoveNotations = listOf("O-O-O")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.notation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }

    @Test
    fun testKingSmallCastling(){
        val board = EmptyChessBoard()
        board.resetBoard()
        board.squares[4][0] = Square(King(ColorPiece.WHITE))
        board.squares[3][0] = Square(Queen(ColorPiece.WHITE))
        board.squares[0][0] = Square(Rook(ColorPiece.WHITE))
        board.squares[7][0] = Square(Rook(ColorPiece.WHITE))
        val piece = board.squares[4][0].piece!!
        val expectedMoveNotations = listOf("O-O")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.notation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }

    @Test
    fun testKingWithoutCastling(){
        val board = EmptyChessBoard()
        board.resetBoard()
        board.squares[0][0] = Square(Rook(ColorPiece.WHITE))
        board.squares[3][0] = Square(Queen(ColorPiece.WHITE))
        board.squares[4][0] = Square(King(ColorPiece.WHITE))
        board.squares[5][0] = Square(Bishop(ColorPiece.WHITE))
        board.squares[7][0] = Square(Rook(ColorPiece.WHITE))
        val piece = board.squares[4][0].piece!!
        val expectedMoveNotations = listOf("Kd2", "Kf2", "Ke2")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.notation }

        Assertions.assertTrue(allowedMovements.size == expectedMoveNotations.size && allowedMovements.containsAll(expectedMoveNotations))
    }

}