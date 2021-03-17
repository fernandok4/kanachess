package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.IPieceSpecialMovementOnFirstMovement
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare
import java.lang.Exception

class PawnMovement(private val piece: IPieceSpecialMovementOnFirstMovement): IPieceMovement {
    override fun calculateAllowedCoordinates(board: Board): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = piece.getCoordenate()
        val isAvailableSquare = possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + (1 * piece.color.factorSide.first), currentCoordinate.second + (1 * piece.color.factorSide.second)))
        if(piece.isFirstMove && isAvailableSquare){
            possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + (2 * piece.color.factorSide.first), currentCoordinate.second + (2 * piece.color.factorSide.second)))
        }
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first + 1, currentCoordinate.second + (1 * piece.color.factorSide.second)))
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first - 1, currentCoordinate.second + (1 * piece.color.factorSide.second)))
        return possibleCoordinates
    }

    fun MutableList<Pair<Int, Int>>.addAvailableSquare(board: Board, coordinate: Pair<Int, Int>): Boolean {
        try {
            coordinate.isOnBoard()
            val squarePiece = board.getSquarePiece(coordinate.first, coordinate.second)
            if(this.contains(coordinate) || squarePiece != null){
                return false
            }
            return this.add(coordinate)
        } catch (e: Exception){
            return false
        }
    }

    fun MutableList<Pair<Int, Int>>.addAttackSquare(board: Board, coordinate: Pair<Int, Int>): Boolean {
        try{
            coordinate.isOnBoard()
            val square = board.getSquare(coordinate.first, coordinate.second)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            if(targetPiece == null){
                return false
            }
            this.containsCoordinate(coordinate)
            piece.targetAvailableSquare(targetPiece)
            return this.add(coordinate)
        } catch (exception: MovementException){
            return false
        }
    }
}