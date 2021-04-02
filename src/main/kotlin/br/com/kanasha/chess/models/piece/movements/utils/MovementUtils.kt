package br.com.kanasha.chess.models.piece.movements.utils

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException

object MovementUtils {

    fun SquareCoordinate.isOnBoard(board: IBoard){
        val boardSize = 0..board.size-1
        if(this.x !in boardSize || this.y !in boardSize){
            throw MovementException()
        }
    }

    fun SquareCoordinate.hasPieceOnCoordinate(board: IBoard): Boolean {
        val squarePiece = board.getSquare(this.x, this.y).piece
        return squarePiece != null
    }

    fun List<MoveNotation>.containsCoordinate(coordinate: SquareCoordinate){
        if(this.any { it.coordinate == coordinate}){
            throw MovementException()
        }
    }

    fun IPiece.targetAvailableSquare(targetPiece: IPiece?) {
        if(targetPiece == null){
            return
        }
        if(targetPiece.color == this.color){
            this.protect(targetPiece)
            throw MovementException()
        } else {
            this.attack(targetPiece)
        }
    }
}