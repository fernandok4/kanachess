package br.com.kanasha.chess.models.piece.specialMovements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPieceSpecial
import br.com.kanasha.chess.models.piece.Rook
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import java.lang.Exception

class CastlingMovement(private val piece: IPieceSpecial): IPieceMovement {

    override fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation> {
        if(!piece.isFirstMove){
            return emptyList()
        }
        if(piece.isUnderAttack){
            return emptyList()
        }
        val rooks = board.piecesGroupedByColor[piece.color]!!.filterIsInstance<Rook>().filter { it.isFirstMove }
        if(rooks.isEmpty()){
            return emptyList()
        }
        val pieceCoordinate = board.getPieceCoordinate(piece)
        val movementList = mutableListOf<MoveNotation>()
        for (rook in rooks) {
            val rookCoordinate = board.getPieceCoordinate(rook)
            var canCastling = true
            val rangeXCoordinate = if(rookCoordinate.x < pieceCoordinate.x) rookCoordinate.x..pieceCoordinate.x else pieceCoordinate.x..rookCoordinate.x
            for(xCoordinate in rangeXCoordinate){
                val square = board.getSquare(xCoordinate, pieceCoordinate.y)
                if(square.isUnderEnemyAttack || (square.piece != null && square.piece != rook && square.piece != piece)){
                    canCastling = false
                    break
                }
            }
            if(canCastling){
                val factorSide = if(rookCoordinate.x < pieceCoordinate.x) -2 else 2
                val coordinateToAdd = SquareCoordinate(pieceCoordinate.x + factorSide, pieceCoordinate.y)
                movementList.add(
                    MoveNotation(
                        coordinateToAdd.toNotationPGN(board, piece),
                        coordinateToAdd,
                        CastlingMovement::class
                    )
                )
            }
        }
        return movementList
    }

    override fun doMovement(board: IBoard, stringNotation: String) {
        val pieceCoordinate = board.getPieceCoordinate(piece)
        val movementNotation = piece.allowedMoves.find { it.notation == stringNotation }!!
        val rookSideOfKing = if(pieceCoordinate.x > movementNotation.coordinate.x) 1 else -1
        val rangeXCoordinate = if(pieceCoordinate.x > movementNotation.coordinate.x) 0..pieceCoordinate.x else pieceCoordinate.x..(board.size - 1)
        var rook: Rook? = null
        for(xCoordinate in rangeXCoordinate){
            val squarePiece = board.getSquare(xCoordinate, pieceCoordinate.y).piece
            if(squarePiece is Rook && squarePiece.isFirstMove && !squarePiece.isDead && squarePiece.color == piece.color){
                rook = squarePiece
            }
        }
        val rookCoordinate = board.getPieceCoordinate(rook ?: throw Exception(""))
        // Moving King
        board.squares[pieceCoordinate.x][pieceCoordinate.y].piece = null
        board.squares[movementNotation.coordinate.x][movementNotation.coordinate.y].piece = piece
        // Moving Rook
        board.squares[rookCoordinate.x][rookCoordinate.y].piece = null
        board.squares[movementNotation.coordinate.x + rookSideOfKing][movementNotation.coordinate.y].piece = rook
    }
}