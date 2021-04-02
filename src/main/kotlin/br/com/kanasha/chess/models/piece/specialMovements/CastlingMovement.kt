package br.com.kanasha.chess.models.piece.specialMovements

import br.com.kanasha.chess.models.board.IBoard
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
            val rangeXCoordinate = if(rookCoordinate.first < pieceCoordinate.first) rookCoordinate.first..pieceCoordinate.first else pieceCoordinate.first..rookCoordinate.first
            for(xCoordinate in rangeXCoordinate){
                val square = board.getSquare(xCoordinate, pieceCoordinate.second)
                if(square.isUnderEnemyAttack || (square.piece != null && square.piece != rook && square.piece != piece)){
                    canCastling = false
                    break
                }
            }
            if(canCastling){
                val factorSide = if(rookCoordinate.first < pieceCoordinate.first) -2 else 2
                val coordinateToAdd = Pair(pieceCoordinate.first + factorSide, pieceCoordinate.second)
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
        val rookSideOfKing = if(pieceCoordinate.first > movementNotation.coordinate.first) 1 else -1
        val rangeXCoordinate = if(pieceCoordinate.first > movementNotation.coordinate.first) 0..pieceCoordinate.first else pieceCoordinate.first..(board.size - 1)
        var rook: Rook? = null
        for(xCoordinate in rangeXCoordinate){
            val squarePiece = board.getSquare(xCoordinate, pieceCoordinate.second).piece
            if(squarePiece is Rook && squarePiece.isFirstMove && !squarePiece.isDead && squarePiece.color == piece.color){
                rook = squarePiece
            }
        }
        val rookCoordinate = board.getPieceCoordinate(rook ?: throw Exception(""))
        // Moving King
        board.squares[pieceCoordinate.first][pieceCoordinate.second].piece = null
        board.squares[movementNotation.coordinate.first][movementNotation.coordinate.second].piece = piece
        // Moving Rook
        board.squares[rookCoordinate.first][rookCoordinate.second].piece = null
        board.squares[movementNotation.coordinate.first + rookSideOfKing][movementNotation.coordinate.second].piece = rook
    }
}