package br.com.kanasha.chess.models.notation

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.Pawn

object ChessNotationRead {

    val yAxisList = arrayOf('1', '2', '3', '4', '5', '6', '7', '8')
    val xAxisList = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')

    fun Pair<Int, Int>.toNotationPGN(board: Board, piece: IPiece): String {
        val pieceCoordinate = board.getCoordenate(piece)
        val targetPiece = board.squares[this.first][this.second].piece
        val targetXAxis = xAxisList[this.first]
        val targetYAxis = yAxisList[this.second]
        val pieceXAxis = xAxisList[pieceCoordinate.first]
        val pieceYAxis = yAxisList[pieceCoordinate.second]
        val combatNotation = if((targetPiece?.color ?: piece.color) != piece.color) "x" else ""
        val cdPiece = if(piece is Pawn && combatNotation == "x") pieceXAxis else piece.cdPiece
        val colorPieces = board.piecesGroupedByColor[piece.color]!!
        val hasAmbiguityPieces = colorPieces.any { it != piece && it.cdPiece == piece.cdPiece && it.allowedMoves.any { it.coordanate.equals(this) } }
        val ambiguityNotation = if(hasAmbiguityPieces && piece !is Pawn) "$pieceXAxis$pieceYAxis" else ""
        return "$cdPiece$ambiguityNotation$combatNotation$targetXAxis$targetYAxis"
    }
}