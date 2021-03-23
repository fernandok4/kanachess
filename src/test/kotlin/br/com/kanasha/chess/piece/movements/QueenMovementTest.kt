package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.Queen
import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import br.com.kanasha.chess.models.board.EmptyChessBoard
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class QueenMovementTest {
    @Test
    fun testQueenMovingLikeBishopMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        board.squares[2][0].piece = Queen(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnEmptyBoard(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Queen(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qa3", "Qd2", "Qe3", "Qf4", "Qg5", "Qh6")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnEnemyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Queen(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qa3", "Qd2", "Qe3", "Qxf4")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnTwoEnemyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Queen(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.BLACK)
        board.squares[0][2].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qxa3", "Qd2", "Qe3", "Qxf4")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnAllyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Queen(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qa3", "Qd2", "Qe3")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnTwoAllyPieceBlocking(){
        val board = EmptyChessBoard()
        board.squares[2][0].piece = Queen(ColorPiece.WHITE)
        board.squares[5][3].piece = Pawn(ColorPiece.WHITE)
        board.squares[1][1].piece = Pawn(ColorPiece.WHITE)
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qd2", "Qe3")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEmptyBoard(){
        val board = EmptyChessBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qf1", "Qg1", "Qh1", "Qa2", "Qa3", "Qa4", "Qa5", "Qa6", "Qa7", "Qa8")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEnemyPieceVerticallyBlocking(){
        val board = EmptyChessBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        board.squares[5][0].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qxf1", "Qa2", "Qa3", "Qa4", "Qa5", "Qa6", "Qa7", "Qa8")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEnemyPieceHorizontallyBlocking(){
        val board = EmptyChessBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        board.squares[0][5].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qf1", "Qg1", "Qh1", "Qa2", "Qa3", "Qa4", "Qa5", "Qxa6")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEnemyPieceBothAxisBlocking(){
        val board = EmptyChessBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        board.squares[0][5].piece = Pawn(ColorPiece.BLACK)
        board.squares[5][0].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qxf1", "Qa2", "Qa3", "Qa4", "Qa5", "Qxa6")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnAllyPieceBothAxisBlocking(){
        val board = EmptyChessBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        board.squares[0][5].piece = Pawn(ColorPiece.WHITE)
        board.squares[5][0].piece = Pawn(ColorPiece.WHITE)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qa2", "Qa3", "Qa4")
        ))
    }

    @Test
    fun testTwoRooksMovementOnAllyPieceBothAxisBlockingConflict(){
        val board = EmptyChessBoard()
        board.squares[0][0].piece = Queen(ColorPiece.WHITE)
        board.squares[7][0].piece = Queen(ColorPiece.WHITE)
        board.squares[5][0].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qa1xf1", "Qa2", "Qa3", "Qa4", "Qa5", "Qa6", "Qa7", "Qa1a8")
        ))
    }
}