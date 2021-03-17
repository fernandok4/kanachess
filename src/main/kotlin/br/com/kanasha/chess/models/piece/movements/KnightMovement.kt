package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare

class KnightMovement(private val piece: IPiece): IPieceMovement {

    override fun calculateAllowedCoordinates(board: Board): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = piece.getCoordenate()
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + 2, currentCoordinate.second + 1))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + 2, currentCoordinate.second - 1))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first - 2, currentCoordinate.second + 1))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first - 2, currentCoordinate.second - 1))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + 1, currentCoordinate.second + 2))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + 1, currentCoordinate.second - 2))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first - 1, currentCoordinate.second + 2))
        possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first - 1, currentCoordinate.second - 2))
        return possibleCoordinates
    }

    fun MutableList<Pair<Int, Int>>.addAvailableSquare(board: Board, coordinate: Pair<Int, Int>): Boolean {
        try {
            coordinate.isOnBoard()
            val square = board.getSquare(coordinate.first, coordinate.second)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            this.containsCoordinate(coordinate)
            piece.targetAvailableSquare(targetPiece)
            return this.add(coordinate)
        } catch (e: MovementException){
            return false
        }
    }
}