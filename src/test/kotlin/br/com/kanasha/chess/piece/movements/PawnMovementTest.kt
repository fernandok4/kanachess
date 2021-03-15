package br.com.kanasha.chess.piece.movements

import br.com.kanasha.chess.models.NormalGameChessBoard
import br.com.kanasha.chess.models.piece.ColorEnum
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.engine.ProcessNormalChessBoardEngine
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PawnMovementTest {

    @Test
    fun testPawnMovementOnFirstRound(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        board.squares[4][1].piece = Pawn(ColorEnum.WHITE, "e2")
        val piece = board.squares[4][1].piece!!
        val expectedMoveNotations = listOf("e3", "e4")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.map { it.moveNotation }.containsAll(expectedMoveNotations))
    }

    @Test
    fun testPawnMovementOnSecondRound(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        // 1. e4 e5
        board.squares[4][1].piece = null
        board.squares[4][3].piece = Pawn(ColorEnum.WHITE, "e4")
        board.squares[4][6].piece = null
        board.squares[4][4].piece = Pawn(ColorEnum.BLACK, "e5")

        val piece = board.squares[4][3].piece!!

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves

        Assertions.assertTrue(allowedMovements.isEmpty())
    }

    @Test
    fun testPawnMovementOnSecondRoundAttackSquare(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        // 1. e4 e5
        board.squares[4][1].piece = null
        board.squares[4][3].piece = Pawn(ColorEnum.WHITE, "e4")
        board.squares[4][3].piece!!.isFirstMove = false
        board.squares[3][6].piece = null
        board.squares[3][4].piece = Pawn(ColorEnum.BLACK, "d5")
        board.squares[3][4].piece!!.isFirstMove = false
        val piece = board.squares[4][3].piece!!
        val expectedMoveNotations = listOf("e5", "exd5")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }

    @Test
    fun testPawnMovementOnSecondRoundAttackSquareAmbiguity(){
        val board = NormalGameChessBoard()
        board.resetBoardPieces()
        // 1. e4 e5
        board.squares[4][1].piece = null
        board.squares[4][3].piece = Pawn(ColorEnum.WHITE, "e4")
        board.squares[4][3].piece!!.isFirstMove = false
        board.squares[3][6].piece = null
        board.squares[3][4].piece = Pawn(ColorEnum.BLACK, "d5")
        board.squares[3][4].piece!!.isFirstMove = false

        board.squares[2][1].piece = null
        board.squares[2][3].piece = Pawn(ColorEnum.WHITE, "c4")
        board.squares[2][3].piece!!.isFirstMove = false
        board.squares[2][6].piece = null
        board.squares[2][4].piece = Pawn(ColorEnum.BLACK, "c5")
        board.squares[2][4].piece!!.isFirstMove = false

        val piece = board.squares[4][3].piece!!
        val expectedMoveNotations = listOf("e5", "exd5")

        ProcessNormalChessBoardEngine(board).process()
        val allowedMovements = piece.allowedMoves.map { it.moveNotation }

        Assertions.assertTrue(allowedMovements.containsAll(expectedMoveNotations))
    }
}