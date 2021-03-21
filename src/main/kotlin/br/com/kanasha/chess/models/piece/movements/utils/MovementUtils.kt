package br.com.kanasha.chess.models.piece.movements.utils

import br.com.kanasha.chess.models.IBoard
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException

object MovementUtils {

    val boardSquareSize = 0..7

    fun Pair<Int, Int>.isOnBoard(){
        if(this.first !in boardSquareSize || this.second !in boardSquareSize){
            throw MovementException()
        }
    }

    fun Pair<Int, Int>.hasPieceOnCoordinate(board: IBoard): Boolean {
        val squarePiece = board.getSquare(this.first, this.second).piece
        return squarePiece != null
    }

    fun List<Pair<Int, Int>>.containsCoordinate(coordinate: Pair<Int, Int>){
        if(this.contains(coordinate)){
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