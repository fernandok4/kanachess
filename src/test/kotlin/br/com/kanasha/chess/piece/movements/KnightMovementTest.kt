package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorPiece
import br.com.kanasha.chess.models.piece.Knight
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KnightMovementTest {

    @Test
    fun testKnightMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        val piece = board.squares[6][0].piece!!
        val expectedMoveNotations = listOf("Nf3", "Nh3")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.notation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }

    @Test
    fun testKnightMovementOnAttackPiece(){
        val board = NormalGameChessBoard()
        board.resetBoard()

        // 1. Nf3 e5
        board.squares[6][0].piece = null
        board.squares[5][2].piece = Knight(ColorPiece.WHITE)
        board.squares[4][6].piece = null
        board.squares[4][4].piece = Pawn(ColorPiece.BLACK)

        val piece = board.squares[5][2].piece!!
        val expectedMoveNotations = listOf("Ng1", "Nh4", "Nd4", "Ng5", "Nxe5")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.notation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }
}