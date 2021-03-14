package br.com.kanasha.chess.models.notation

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.piece.IPiece

object ChessNotationRead {

    val yAxisList = arrayOf('1', '2', '3', '4', '5', '6', '7', '8')
    val xAxisList = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

    fun Pair<Int, Int>.toNotation(board: Board, piece: IPiece): String {
        val targetPiece = board.squares[this.first][this.second].piece
        val xAxis = yAxisList[this.second]
        val yAxis = xAxisList[this.first]
        val cdPiece = piece.cdPiece
        val colorPieces = board.piecesGroupedByColor[piece.color]!!
        val hasAmbiguityPieces = colorPieces.any { it != piece && it.cdPiece == piece.cdPiece && it.allowedCoordinates.any { it.equals(this) } }
        val ambiguityNotation = if(hasAmbiguityPieces) piece.currentNotationSquare else ""
        val combatNotation = if((targetPiece?.color ?: piece.color) != piece.color) "x" else ""
        return "$cdPiece$ambiguityNotation$combatNotation$yAxis$xAxis"
    }

    fun IPiece.getCoordenate(): Pair<Int, Int> {
        val xCoordenate = xAxisList.indexOf(currentNotationSquare[0])
        val yCoordenate = yAxisList.indexOf(currentNotationSquare[1])
        return Pair(xCoordenate, yCoordenate)
    }
}