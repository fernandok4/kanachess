package br.com.kanasha.chess.engine

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.NormalGameChessBoard
import br.com.kanasha.chess.service.DoChessMovementService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
//       5. O-O Be7
            DoChessMovementService(board, "O-O").execute()
            DoChessMovementService(board, "Be7").execute()
//       6. Re1 b5
            DoChessMovementService(board, "Re1").execute()
            DoChessMovementService(board, "b5").execute()
//       7. Bb3 d6
            DoChessMovementService(board, "Bb3").execute()
            DoChessMovementService(board, "d6").execute()
//       8. c3 O-O
            DoChessMovementService(board, "c3").execute()
            DoChessMovementService(board, "O-O").execute()
//       9. h3 Na5
            DoChessMovementService(board, "h3").execute()
            DoChessMovementService(board, "Na5").execute()
//       10. Bc2 c5
            DoChessMovementService(board, "Bc2").execute()
            DoChessMovementService(board, "c5").execute()
//       11. d4 cxd4
            DoChessMovementService(board, "d4").execute()
            DoChessMovementService(board, "cxd4").execute()
//       12. cxd4 Bb7
            DoChessMovementService(board, "cxd4").execute()
            DoChessMovementService(board, "Bb7").execute()
//       13. d5 Rc8
            DoChessMovementService(board, "d5").execute()
            DoChessMovementService(board, "Rc8").execute()
//       14. b3 Qc7
            DoChessMovementService(board, "b3").execute()
            DoChessMovementService(board, "Qc7").execute()
//       15. Bd3 Nxe4
            DoChessMovementService(board, "Bd3").execute()
            DoChessMovementService(board, "Nxe4").execute()
        }
//        val gson = GsonBuilder().create()
//        val teste = gson.toJson(board)
        println(time1)
        Assertions.assertTrue(time1 < 1000)
    }
}