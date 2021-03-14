package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard

class PawnMovement(private val piece: Pawn): IPieceMovement {
    override fun calculateAllowedCoordinates(board: Board): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = piece.getCoordenate()
        val isAvailableSquare = possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first, currentCoordinate.second + 1))
        if(piece.isFirstMove && isAvailableSquare){
            possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first, currentCoordinate.second + 2))
        }
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first + 1, currentCoordinate.second + 1))
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first - 1, currentCoordinate.second + 1))
        return possibleCoordinates
    }

    fun MutableList<Pair<Int, Int>>.addAvailableSquare(board: Board, coordinate: Pair<Int, Int>): Boolean {
        if(!coordinate.isOnBoard()){
            return false
        }
        val squarePiece = board.getSquarePiece(coordinate.first, coordinate.second)
        if(this.contains(coordinate) || squarePiece != null){
            return false
        }
        return this.add(coordinate)
    }

    fun MutableList<Pair<Int, Int>>.addAttackSquare(board: Board, coordinate: Pair<Int, Int>): Boolean {
        if(!coordinate.isOnBoard()){
            return false
        }
        val squarePiece = board.getSquarePiece(coordinate.first, coordinate.second)
        if(this.contains(coordinate) || squarePiece == null || squarePiece.color == piece.color){
            return false
        }
        return this.add(coordinate)
    }
}