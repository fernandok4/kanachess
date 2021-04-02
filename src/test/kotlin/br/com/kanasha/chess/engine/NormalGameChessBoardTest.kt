package br.com.kanasha.chess.engine

import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.service.DoChessMovementService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class NormalGameChessBoardTest {

    @Test
    fun game1Test(){
        val board = NormalGameChessBoard()
        board.resetBoard()
        val time1 = measureTimeMillis {
//       1. e4 e5
            DoChessMovementService(board, "e4").execute()
            DoChessMovementService(board, "e5").execute()
//       2. Nf3 Nc6
            DoChessMovementService(board, "Nf3").execute()
            DoChessMovementService(board, "Nc6").execute()
//       3. Bb5 a6
            DoChessMovementService(board, "Bb5").execute()
            DoChessMovementService(board, "a6").execute()
//       4. Ba4 Nf6
            DoChessMovementService(board, "Ba4").execute()
            DoChessMovementService(board, "Nf6").execute()
//       5. Ba4 Nf6
            DoChessMovementService(board, "O-O").execute()
            DoChessMovementService(board, "Be7").execute()
        }
        println(time1)
        Assertions.assertTrue(time1 < 1000)
    }
}