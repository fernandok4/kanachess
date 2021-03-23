package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import br.com.kanasha.chess.models.board.EmptyChessBoard
import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.King
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.Rook
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KingMovementTest {

    @Test
    fun testKingMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        val piece = board.squares[4][0].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testKingMovementOnSecondRound(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        // 1. e4 e5
        board.squares[4][1].piece = null
        board.squares[4][3].piece = Pawn(ColorPiece.WHITE)
        (board.squares[4][3].piece!! as Pawn).isFirstMove = false
        board.squares[3][6].piece = null
        board.squares[3][4].piece = Pawn(ColorPiece.BLACK)
        (board.squares[3][4].piece!! as Pawn).isFirstMove = false
        val piece = board.squares[4][0].piece!!
        val expectedMoveNotations = listOf("Ke2")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }

    @Test
    fun testKingMovementOnEmptyBoard(){
        val board = EmptyChessBoard()
        board.squares[4][3].piece = King(ColorPiece.WHITE)
        val piece = board.squares[4][3].piece!!
        val expectedNotations = listOf("Ke5", "Kf5", "Kf4", "Kf3", "Ke3", "Kd4", "Kd5", "Kd3")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testKingMovementOnEmptyBoardWithAttack(){
        val board = EmptyChessBoard()
        board.squares[4][3].piece = King(ColorPiece.WHITE)
        board.squares[4][4].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[4][3].piece!!
        val expectedNotations = listOf("Kxe5", "Kf5", "Kf3", "Ke3", "Kd5", "Kd3")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testKingMovementOnEmptyBoardWithEnemyKingOpposite(){
        val board = EmptyChessBoard()
        board.squares[4][3].piece = King(ColorPiece.WHITE)
        board.squares[4][5].piece = King(ColorPiece.BLACK)
        val piece = board.squares[4][3].piece!!
        val expectedNotations = listOf("Kf4", "Kf3", "Ke3", "Kd4", "Kd3")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations))
    }

    @Test
    fun testKingAttackProtectedPiece(){
        val board = EmptyChessBoard()
        board.squares[4][3].piece = King(ColorPiece.WHITE)
        board.squares[4][4].piece = Pawn(ColorPiece.BLACK)
        board.squares[5][5].piece = Pawn(ColorPiece.BLACK)
        val piece = board.squares[4][3].piece!!
        val expectedNotations = listOf("Kf5", "Kf3", "Ke3", "Kd5", "Kd3")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations) && !allowedMovements.contains("Kxe5"))
    }

    @Test
    fun testKingBlockedByARook(){
        val board = EmptyChessBoard()
        board.squares[4][3].piece = King(ColorPiece.WHITE)
        board.squares[5][7].piece = Rook(ColorPiece.BLACK)
        val piece = board.squares[4][3].piece!!
        val expectedNotations = listOf("Ke3", "Kd4", "Kd5", "Kd3", "Ke5")
        val expectedNotationsNotContains = listOf("Kf3", "Kf4", "Kf5")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedNotations) && !allowedMovements.any { expectedNotationsNotContains.contains(it) })
    }
}