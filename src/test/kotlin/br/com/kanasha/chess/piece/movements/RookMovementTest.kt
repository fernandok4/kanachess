package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.Rook
import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RookMovementTest {

    @Test
    fun testRookMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testRookMovementOnEmptyBoard(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        val piece = board.squares[0][0].piece!!
        val expectedNotations = listOf("Rb1", "Rc1", "Rd1", "Re1", "Rf1", "Rg1", "Rh1", "Ra2", "Ra3", "Ra4", "Ra5", "Ra6", "Ra7", "Ra8")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testRookMovementOnEnemyPieceVerticallyBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        board.squares[5][0].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!
        val expectedNotations = listOf("Rb1", "Rc1", "Rd1", "Re1", "Rxf1", "Ra2", "Ra3", "Ra4", "Ra5", "Ra6", "Ra7", "Ra8")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testRookMovementOnEnemyPieceHorizontallyBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        board.squares[0][5].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!
        val expectedNotations = listOf("Rb1", "Rc1", "Rd1", "Re1", "Rf1", "Rg1", "Rh1", "Ra2", "Ra3", "Ra4", "Ra5", "Rxa6")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testRookMovementOnEnemyPieceBothAxisBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        board.squares[0][5].piece = Pawn(ColorPiece.BLACK)
        board.squares[5][0].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!
        val expectedNotations = listOf("Rb1", "Rc1", "Rd1", "Re1", "Rxf1", "Ra2", "Ra3", "Ra4", "Ra5", "Rxa6")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testRookMovementOnAllyPieceBothAxisBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        board.squares[0][5].piece = Pawn(ColorPiece.WHITE)
        board.squares[5][0].piece = Pawn(ColorPiece.WHITE)
        val piece = board.squares[0][0].piece!!
        val expectedNotations = listOf("Rb1", "Rc1", "Rd1", "Re1", "Ra2", "Ra3", "Ra4")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testTwoRooksMovementOnAllyPieceBothAxisBlockingConflict(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorPiece.WHITE)
        board.squares[7][0].piece = Rook(ColorPiece.WHITE)
        board.squares[5][0].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!
        val expecetedNotations = listOf("Rb1", "Rc1", "Rd1", "Re1", "Ra1xf1", "Ra2", "Ra3", "Ra4", "Ra5", "Ra6", "Ra7", "Ra8")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expecetedNotations))
    }
}