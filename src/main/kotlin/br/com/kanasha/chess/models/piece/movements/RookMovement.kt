package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare

class RookMovement(private val piece: IPiece): IPieceMovement {

    override fun calculateAllowedCoordinates(board: Board): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = piece.getCoordenate()
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, 1, 0)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, -1, 0)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, 0, 1)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, 0, -1)
        return possibleCoordinates
    }

    fun MutableList<Pair<Int, Int>>.addAvailableSquare(board: Board, coordinate: Pair<Int, Int>, xSquares: Int, ySquares: Int) {
        try{
            coordinate.isOnBoard()
            if(piece.getCoordenate().equals(coordinate)){
                this.addAvailableSquare(board, Pair(coordinate.first + xSquares, coordinate.second + ySquares), xSquares, ySquares)
                return
            }
            val square = board.getSquare(coordinate.first, coordinate.second)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            this.containsCoordinate(coordinate)
            piece.targetAvailableSquare(targetPiece)
            this.add(coordinate)
            if(targetPiece != null){
                return
            }
            this.addAvailableSquare(board, Pair(coordinate.first + xSquares, coordinate.second + ySquares), xSquares, ySquares)
        } catch (e: MovementException){
            return
        }
    }
}