package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.gson.exclusionStrategy.Exclude
import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPieceSpecial
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.hasPieceOnCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare
import java.lang.Exception

class PawnMovement(@field:Exclude private val piece: IPieceSpecial): IPieceMovement {
    override fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation> {
        val possibleCoordinates = mutableListOf<MoveNotation>()
        val currentCoordinate = board.getPieceCoordinate(piece)
        val isAvailableSquare = possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x + (1 * piece.color.factorSide.x), currentCoordinate.y + (1 * piece.color.factorSide.y)))
        if(piece.isFirstMove && isAvailableSquare){
            possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x + (2 * piece.color.factorSide.x), currentCoordinate.y + (2 * piece.color.factorSide.y)))
        }
        possibleCoordinates.addAttackSquare(board, SquareCoordinate(currentCoordinate.x + 1, currentCoordinate.y + (1 * piece.color.factorSide.y)))
        possibleCoordinates.addAttackSquare(board, SquareCoordinate(currentCoordinate.x - 1, currentCoordinate.y + (1 * piece.color.factorSide.y)))
        return possibleCoordinates
    }

    private fun MutableList<MoveNotation>.addAvailableSquare(board: IBoard, coordinate: SquareCoordinate): Boolean {
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

    private fun MutableList<MoveNotation>.addAttackSquare(board: IBoard, coordinate: SquareCoordinate): Boolean {
        try{
            coordinate.isOnBoard(board)
            val square = board.getSquare(coordinate.x, coordinate.y)
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
        board.squares[pieceCoordinate.x][pieceCoordinate.y].piece = null
        board.squares[movementNotation.coordinate.x][movementNotation.coordinate.y].piece?.isDead = true
        board.squares[movementNotation.coordinate.x][movementNotation.coordinate.y].piece = piece
    }
}