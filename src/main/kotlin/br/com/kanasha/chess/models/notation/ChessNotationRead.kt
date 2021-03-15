package br.com.kanasha.chess.models.notation

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.Pawn

object ChessNotationRead {

    val yAxisList = arrayOf('1', '2', '3', '4', '5', '6', '7', '8')
    val xAxisList = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

    fun Pair<Int, Int>.toNotationPGN(board: Board, piece: IPiece): String {
        val targetPiece = board.squares[this.first][this.second].piece
        val xAxis = yAxisList[this.second]
        val yAxis = xAxisList[this.first]
        val combatNotation = if((targetPiece?.color ?: piece.color) != piece.color) "x" else ""
        val cdPiece = if(piece is Pawn && combatNotation == "x") piece.currentNotationSquare[0] else piece.cdPiece
        val colorPieces = board.piecesGroupedByColor[piece.color]!!
        val hasAmbiguityPieces = colorPieces.any { it != piece && it.cdPiece == piece.cdPiece && it.allowedCoordinates.any { it.equals(this) } }
        val ambiguityNotation = if(hasAmbiguityPieces && piece !is Pawn) piece.currentNotationSquare else ""
        return "$cdPiece$ambiguityNotation$combatNotation$yAxis$xAxis"
    }

    fun IPiece.getCoordenate(): Pair<Int, Int> {
        val xCoordenate = xAxisList.indexOf(currentNotationSquare[0])
        val yCoordenate = yAxisList.indexOf(currentNotationSquare[1])
        return Pair(xCoordenate, yCoordenate)
    }
}