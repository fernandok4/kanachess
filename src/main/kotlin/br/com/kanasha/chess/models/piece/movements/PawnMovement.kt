package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPieceSpecial
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.hasPieceOnCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare
import java.lang.Exception

class PawnMovement(private val piece: IPieceSpecial): IPieceMovement {
    override fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation> {
        val possibleCoordinates = mutableListOf<MoveNotation>()
        val currentCoordinate = board.getPieceCoordinate(piece)
        val isAvailableSquare = possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + (1 * piece.color.factorSide.first), currentCoordinate.second + (1 * piece.color.factorSide.second)))
        if(piece.isFirstMove && isAvailableSquare){
            possibleCoordinates.addAvailableSquare(board, Pair(currentCoordinate.first + (2 * piece.color.factorSide.first), currentCoordinate.second + (2 * piece.color.factorSide.second)))
        }
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first + 1, currentCoordinate.second + (1 * piece.color.factorSide.second)))
        possibleCoordinates.addAttackSquare(board, Pair(currentCoordinate.first - 1, currentCoordinate.second + (1 * piece.color.factorSide.second)))
        return possibleCoordinates
    }

    private fun MutableList<MoveNotation>.addAvailableSquare(board: IBoard, coordinate: Pair<Int, Int>): Boolean {
        return try {
            coordinate.isOnBoard(board)
            if(coordinate.hasPieceOnCoordinate(board)){
                throw MovementException()
            }
            this.containsCoordinate(coordinate)
            this.add(
                MoveNotation(
                    coordinate.toNotationPGN(board, piece),
                    coordinate,
                    PawnMovement::class
                )
            )
        } catch (e: Exception){
            false
        }
    }

    private fun MutableList<MoveNotation>.addAttackSquare(board: IBoard, coordinate: Pair<Int, Int>): Boolean {
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
            return this.add(
                MoveNotation(
                    coordinate.toNotationPGN(board, piece),
                    coordinate,
                    PawnMovement::class
                )
            )
        } catch (exception: MovementException){
            return false
        }
    }

    override fun doMovement(board: IBoard, stringNotation: String){
        val pieceCoordinate = board.getPieceCoordinate(piece)
        val movementNotation = piece.allowedMoves.find { it.notation == stringNotation }!!
        board.squares[pieceCoordinate.first][pieceCoordinate.second].piece = null
        board.squares[movementNotation.coordinate.first][movementNotation.coordinate.second].piece?.isDead = true
        board.squares[movementNotation.coordinate.first][movementNotation.coordinate.second].piece = piece
    }
}