package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.Bishop
import br.com.kanasha.chess.models.piece.ColorEnum
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.service.DiscoverAllowedMovementsService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BishopMovementTest {

    @Test
    fun testBishopMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        board.squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        val piece = board.squares[2][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testBishopMovementOnEmptyBoard(){
        val board = Board()
        board.squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        val piece = board.squares[2][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Bb2", "Ba3", "Bd2", "Be3", "Bf4", "Bg5", "Bh6")
        ))
    }

    @Test
    fun testBishopMovementOnEnemyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.BLACK, "f4")
        val piece = board.squares[2][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Bb2", "Ba3", "Bd2", "Be3", "Bxf4")
        ))
    }

    @Test
    fun testBishopMovementOnTwoEnemyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.BLACK, "f4")
        board.squares[0][2].piece = Pawn(ColorEnum.BLACK, "a3")
        val piece = board.squares[2][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Bb2", "Bxa3", "Bd2", "Be3", "Bxf4")
        ))
    }

    @Test
    fun testBishopMovementOnAllyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.WHITE, "f4")
        val piece = board.squares[2][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Bb2", "Ba3", "Bd2", "Be3")
        ))
    }

    @Test
    fun testBishopMovementOnTwoAllyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Bishop(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.WHITE, "f4")
        board.squares[1][1].piece = Pawn(ColorEnum.WHITE, "b2")
        val piece = board.squares[2][0].piece!!

        DiscoverAllowedMovementsService(board).discover()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Bd2", "Be3")
        ))
    }
}