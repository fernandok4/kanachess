package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.piece.IPieceSpecial
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.hasPieceOnCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare
import java.lang.Exception

class PawnMovement(private val piece: IPieceSpecial): IPieceMovement {
    override fun calculateAllowedCoordinates(board: IBoard): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = board.getPieceCoordenate(piece)
        val isAvailableSquare = possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + (1 * piece.color.factorSide.first), currentCoordinate.second + (1 * piece.color.factorSide.second)))
        if(piece.isFirstMove && isAvailableSquare){
            possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + (2 * piece.color.factorSide.first), currentCoordinate.second + (2 * piece.color.factorSide.second)))
        }
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first + 1, currentCoordinate.second + (1 * piece.color.factorSide.second)))
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first - 1, currentCoordinate.second + (1 * piece.color.factorSide.second)))
        return possibleCoordinates
    }

    fun MutableList<Pair<Int, Int>>.addAvailableSquare(board: IBoard, coordinate: Pair<Int, Int>): Boolean {
        try {
            coordinate.isOnBoard(board)
            if(coordinate.hasPieceOnCoordinate(board)){
                throw MovementException()
            }
            this.containsCoordinate(coordinate)
            return this.add(coordinate)
        } catch (e: Exception){
            return false
        }
    }

    fun MutableList<Pair<Int, Int>>.addAttackSquare(board: IBoard, coordinate: Pair<Int, Int>): Boolean {
        try{
            coordinate.isOnBoard(board)
            val square = board.getSquare(coordinate.first, coordinate.second)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            if(!coordinate.hasPieceOnCoordinate(board)){
                throw MovementException()
            }
            this.containsCoordinate(coordinate)
            piece.targetAvailableSquare(targetPiece)
            return this.add(coordinate)
        } catch (exception: MovementException){
            return false
        }
    }
}