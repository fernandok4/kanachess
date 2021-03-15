package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorEnum
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.Queen
import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class QueenMovementTest {
    @Test
    fun testQueenMovingLikeBishopMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        board.squares[2][0].piece = Queen(ColorEnum.WHITE, "c1")
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnEmptyBoard(){
        val board = Board()
        board.squares[2][0].piece = Queen(ColorEnum.WHITE, "c1")
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qa3", "Qd2", "Qe3", "Qf4", "Qg5", "Qh6")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnEnemyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Queen(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.BLACK, "f4")
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qa3", "Qd2", "Qe3", "Qxf4")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnTwoEnemyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Queen(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.BLACK, "f4")
        board.squares[0][2].piece = Pawn(ColorEnum.BLACK, "a3")
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qxa3", "Qd2", "Qe3", "Qxf4")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnAllyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Queen(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.WHITE, "f4")
        val piece = board.squares[2][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb2", "Qa3", "Qd2", "Qe3")
        ))
    }

    @Test
    fun testQueenMovingLikeBishopMovementOnTwoAllyPieceBlocking(){
        val board = Board()
        board.squares[2][0].piece = Queen(ColorEnum.WHITE, "c1")
        board.squares[5][3].piece = Pawn(ColorEnum.WHITE, "f4")
        board.squares[1][1].piece = Pawn(ColorEnum.WHITE, "Q2")
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
        board.resetBoardPieces()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEmptyBoard(){
        val board = Board()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qf1", "Qg1", "Qh1", "Qa2", "Qa3", "Qa4", "Qa5", "Qa6", "Qa7", "Qa8")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEnemyPieceVerticallyBlocking(){
        val board = Board()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        board.squares[5][0].piece = Pawn(ColorEnum.BLACK, "f1")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qxf1", "Qa2", "Qa3", "Qa4", "Qa5", "Qa6", "Qa7", "Qa8")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEnemyPieceHorizontallyBlocking(){
        val board = Board()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        board.squares[0][5].piece = Pawn(ColorEnum.BLACK, "a6")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qf1", "Qg1", "Qh1", "Qa2", "Qa3", "Qa4", "Qa5", "Qxa6")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnEnemyPieceBothAxisBlocking(){
        val board = Board()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        board.squares[0][5].piece = Pawn(ColorEnum.BLACK, "a6")
        board.squares[5][0].piece = Pawn(ColorEnum.BLACK, "f1")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qxf1", "Qa2", "Qa3", "Qa4", "Qa5", "Qxa6")
        ))
    }

    @Test
    fun testQueenMovingLikeRookMovementOnAllyPieceBothAxisBlocking(){
        val board = Board()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        board.squares[0][5].piece = Pawn(ColorEnum.WHITE, "a6")
        board.squares[5][0].piece = Pawn(ColorEnum.WHITE, "f1")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qa2", "Qa3", "Qa4")
        ))
    }

    @Test
    fun testTwoRooksMovementOnAllyPieceBothAxisBlockingConflict(){
        val board = Board()
        board.squares[0][0].piece = Queen(ColorEnum.WHITE, "a1")
        board.squares[7][0].piece = Queen(ColorEnum.WHITE, "h1")
        board.squares[5][0].piece = Pawn(ColorEnum.BLACK, "f1")
        val piece = board.squares[0][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(
            listOf("Qb1", "Qc1", "Qd1", "Qe1", "Qa1xf1", "Qa2", "Qa3", "Qa4", "Qa5", "Qa6", "Qa7", "Qa1a8")
        ))
    }
}