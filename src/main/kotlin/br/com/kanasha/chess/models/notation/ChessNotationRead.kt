package br.com.kanasha.chess.models.notation

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.King
import br.com.kanasha.chess.models.piece.Pawn
import kotlin.String
import kotlin.math.absoluteValue

object ChessNotationRead {

    val yAxisList = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")
    val xAxisList = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p")

    fun SquareCoordinate.toNotationPGN(board: IBoard, piece: IPiece): String {
        if(piece is King){
            val pieceCoordinate = board.getPieceCoordinate(piece)
            val castlingSide = this.x - pieceCoordinate.x
            val isCastling = castlingSide.absoluteValue >= 2
            if(isCastling){
                // If castlingSide is bigger than 0 then it will be a small castling otherwise it's the big castling
                // val squareCastle = (if(castlingSide > 0) board.getSquare(board.size - 1, pieceCoordinate.second) else board.getSquare(0, pieceCoordinate.second))
                return if(castlingSide > 0) "O-O" else "O-O-O"
            }
        }
        val pieceCoordinate = board.getPieceCoordinate(piece)
        val targetPiece = board.squares[this.x][this.y].piece
        val targetXAxis = xAxisList[this.x]
        val targetYAxis = yAxisList[this.y]
        val pieceXAxis = xAxisList[pieceCoordinate.x]
        val pieceYAxis = yAxisList[pieceCoordinate.y]
        val combatNotation = if((targetPiece?.color ?: piece.color) != piece.color) "x" else ""
        val cdPiece = if(piece is Pawn && combatNotation == "x") pieceXAxis else piece.cdPiece
        val colorPieces = board.piecesGroupedByColor[piece.color]!!
        val hasAmbiguityPieces = colorPieces.any { it != piece && it.cdPiece == piece.cdPiece && it.allowedMoves.any { it.coordinate.equals(this) } }
        val ambiguityNotation = if(hasAmbiguityPieces && piece !is Pawn) "$pieceXAxis$pieceYAxis" else ""
        return "$cdPiece$ambiguityNotation$combatNotation$targetXAxis$targetYAxis"
    }
}