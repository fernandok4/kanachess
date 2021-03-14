package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorEnum
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.Rook
import br.com.kanasha.chess.service.DiscoverAllowedMovementsService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RookMovementTest {

    @Test
    fun testRookMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testRookMovementOnEmptyBoard(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Rb1", "Rc1", "Rd1", "Re1", "Rf1", "Rg1", "Rh1", "Ra2", "Ra3", "Ra4", "Ra5", "Ra6", "Ra7", "Ra8")
        ))
    }

    @Test
    fun testRookMovementOnEnemyPieceVerticallyBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        board.squares[5][0].piece = Pawn(ColorEnum.BLACK, "f1")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Rb1", "Rc1", "Rd1", "Re1", "Rxf1", "Ra2", "Ra3", "Ra4", "Ra5", "Ra6", "Ra7", "Ra8")
        ))
    }

    @Test
    fun testRookMovementOnEnemyPieceHorizontallyBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        board.squares[0][5].piece = Pawn(ColorEnum.BLACK, "a6")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Rb1", "Rc1", "Rd1", "Re1", "Rf1", "Rg1", "Rh1", "Ra2", "Ra3", "Ra4", "Ra5", "Rxa6")
        ))
    }

    @Test
    fun testRookMovementOnEnemyPieceBothAxisBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        board.squares[0][5].piece = Pawn(ColorEnum.BLACK, "a6")
        board.squares[5][0].piece = Pawn(ColorEnum.BLACK, "f1")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Rb1", "Rc1", "Rd1", "Re1", "Rxf1", "Ra2", "Ra3", "Ra4", "Ra5", "Rxa6")
        ))
    }

    @Test
    fun testRookMovementOnAllyPieceBothAxisBlocking(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        board.squares[0][5].piece = Pawn(ColorEnum.WHITE, "a6")
        board.squares[5][0].piece = Pawn(ColorEnum.WHITE, "f1")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Rb1", "Rc1", "Rd1", "Re1", "Ra2", "Ra3", "Ra4")
        ))
    }

    @Test
    fun testTwoRooksMovementOnAllyPieceBothAxisBlockingConflict(){
        val board = Board()
        board.squares[0][0].piece = Rook(ColorEnum.WHITE, "a1")
        board.squares[7][0].piece = Rook(ColorEnum.WHITE, "h1")
        board.squares[5][0].piece = Pawn(ColorEnum.BLACK, "f1")
        val piece = board.squares[0][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Rb1", "Rc1", "Rd1", "Re1", "Ra1xf1", "Ra2", "Ra3", "Ra4", "Ra5", "Ra6", "Ra7", "Ra8")
        ))
    }
}