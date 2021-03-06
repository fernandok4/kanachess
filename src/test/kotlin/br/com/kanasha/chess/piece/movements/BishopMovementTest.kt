package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.Bishop
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import br.com.kanasha.chess.models.board.EmptyChessBoard
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BishopMovementTest {

    @Test
    fun testBishopMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        board.squares[2][0].piece = Bishop(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testBishopMovementOnEmptyBoard(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Bishop(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.notation }.containsAll(
            listOf("Bb2", "Ba3", "Bd2", "Be3", "Bf4", "Bg5", "Bh6")
        ))
    }

    @Test
    fun testBishopMovementOnEnemyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Bishop(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.notation }.containsAll(
            listOf("Bb2", "Ba3", "Bd2", "Be3", "Bxf4")
        ))
    }

    @Test
    fun testBishopMovementOnTwoEnemyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Bishop(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.BLACK)
        board.squares[0][2].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.notation }.containsAll(
            listOf("Bb2", "Bxa3", "Bd2", "Be3", "Bxf4")
        ))
    }

    @Test
    fun testBishopMovementOnAllyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Bishop(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.notation }.containsAll(
            listOf("Bb2", "Ba3", "Bd2", "Be3")
        ))
    }

    @Test
    fun testBishopMovementOnTwoAllyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Bishop(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.WHITE)
        board.squares[1][1].piece = Pawn(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.notation }.containsAll(
            listOf("Bd2", "Be3")
        ))
    }
}