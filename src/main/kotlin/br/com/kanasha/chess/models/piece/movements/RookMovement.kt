package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard

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
        if(!coordinate.isOnBoard()){
            return
        }
        if(piece.getCoordenate().equals(coordinate)){
            this.addAvailableSquare(board, Pair(coordinate.first + xSquares, coordinate.second + ySquares), xSquares, ySquares)
            return
        }
        val square = board.getSquare(coordinate.first, coordinate.second)
        val squarePiece = square.piece
        if(board.colorRound != piece.color){
            square.isUnderEnemyAttack = true
        }
        if(this.contains(coordinate)){
            return
        }
        if(squarePiece != null){
            if(squarePiece.color == piece.color){
                piece.protect(squarePiece)
                return
            } else {
                piece.attack(squarePiece)
            }
        }
        this.add(coordinate)
        if(squarePiece != null){
            return
        }
        this.addAvailableSquare(board, Pair(coordinate.first + xSquares, coordinate.second + ySquares), xSquares, ySquares)
    }
}