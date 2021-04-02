package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare

class KnightMovement(private val piece: IPiece): IPieceMovement {

    override fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation> {
        val possibleCoordinates = mutableListOf<MoveNotation>()
        val currentCoordinate = board.getPieceCoordinate(piece)
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x + 2, currentCoordinate.y + 1))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x + 2, currentCoordinate.y - 1))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x - 2, currentCoordinate.y + 1))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x - 2, currentCoordinate.y - 1))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x + 1, currentCoordinate.y + 2))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x + 1, currentCoordinate.y - 2))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x - 1, currentCoordinate.y + 2))
        possibleCoordinates.addAvailableSquare(board, SquareCoordinate(currentCoordinate.x - 1, currentCoordinate.y - 2))
        return possibleCoordinates
    }

    private fun MutableList<MoveNotation>.addAvailableSquare(board: IBoard, coordinate: SquareCoordinate): Boolean {
        try {
            coordinate.isOnBoard(board)
            val square = board.getSquare(coordinate.x, coordinate.y)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            this.containsCoordinate(coordinate)
            piece.targetAvailableSquare(targetPiece)
            return this.add(
                MoveNotation(
                    coordinate.toNotationPGN(board, piece),
                    coordinate,
                    KnightMovement::class
                )
            )
        } catch (e: MovementException){
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