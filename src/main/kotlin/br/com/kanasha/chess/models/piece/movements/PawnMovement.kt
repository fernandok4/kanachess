package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.notation.ChessNotationRead.getCoordenate
import br.com.kanasha.chess.models.piece.Pawn
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard

class PawnMovement(private val piece: Pawn): IPieceMovement {
    override fun calculateAllowedCoordinates(board: Board): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = piece.getCoordenate()
        val isAvailableSquare = possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first, currentCoordinate.second + (1 * piece.color.factorSide)))
        if(piece.isFirstMove && isAvailableSquare){
            possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first, currentCoordinate.second + (2 * piece.color.factorSide)))
        }
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first + (1 * piece.color.factorSide), currentCoordinate.second + (1 * piece.color.factorSide)))
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first - (1 * piece.color.factorSide), currentCoordinate.second + (1 * piece.color.factorSide)))
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
        val square = board.getSquare(coordinate.first, coordinate.second)
        val squarePiece = square.piece
        if(board.colorRound != piece.color){
            square.isUnderEnemyAttack = true
        }
        if(this.contains(coordinate) || squarePiece == null){
            return false
        }
        if(squarePiece.color == piece.color){
            piece.protect(squarePiece)
            return false
        } else {
            piece.attack(squarePiece)
        }
        return this.add(coordinate)
    }
}