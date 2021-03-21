package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.IBoard
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.King
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare

class KingMovement(private val piece: IPiece): IPieceMovement {
    override fun calculateAllowedCoordinates(board: IBoard): List<Pair<Int, Int>> {
        val possibleCoordinates = mutableListOf<Pair<Int, Int>>()
        val currentCoordinate = board.getPieceCoordenate(piece)
        currentCoordinate.getArroundSquares().forEach {
            possibleCoordinates.addAvailableSquare(board, it)
        }
        return possibleCoordinates
    }

    fun MutableList<Pair<Int, Int>>.addAvailableSquare(board: IBoard, coordinate: Pair<Int, Int>) {
        try {
            coordinate.isOnBoard()
            val square = board.getSquare(coordinate.first, coordinate.second)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            piece.targetAvailableSquare(targetPiece)
            this.containsCoordinate(coordinate)
            if(square.isUnderEnemyAttack || targetPiece?.isUnderProtection ?: false){
                return
            }
            val hasOppositeEnemyKing = coordinate.getArroundSquares().hasOppositeEnemyKing(board)
            if(hasOppositeEnemyKing){
                return
            }
            this.add(coordinate)
        } catch (e: MovementException){
            return
        }
    }

    fun Pair<Int, Int>.getArroundSquares() = listOf(Pair(this.first + 1, this.second + 1),
        Pair(this.first + 1, this.second - 1),
        Pair(this.first - 1, this.second + 1),
        Pair(this.first - 1, this.second - 1),
        Pair(this.first, this.second + 1),
        Pair(this.first, this.second - 1),
        Pair(this.first - 1, this.second),
        Pair(this.first + 1, this.second))

    fun List<Pair<Int, Int>>.hasOppositeEnemyKing(board: IBoard) = this.any {
        val squarePiece = board.getSquare(it.first, it.second).piece
        squarePiece is King && squarePiece.color != piece.color
    }
}