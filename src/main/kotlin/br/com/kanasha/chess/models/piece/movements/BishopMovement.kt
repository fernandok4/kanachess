package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare

class BishopMovement(private val piece: IPiece): IPieceMovement {

    override fun calculateAllowedCoordinates(board: IBoard): MutableList<MoveNotation> {
        val possibleCoordinates = mutableListOf<MoveNotation>()
        val currentCoordinate = board.getPieceCoordinate(piece)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, 1, 1)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, -1, -1)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, -1, 1)
        possibleCoordinates.addAvailableSquare(board, currentCoordinate, 1, -1)
        return possibleCoordinates
    }

    private fun MutableList<MoveNotation>.addAvailableSquare(board: IBoard, coordinate: Pair<Int, Int>, xSquares: Int, ySquares: Int) {
        try {
            coordinate.isOnBoard(board)
            if(board.getPieceCoordinate(piece) == coordinate){
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
            this.add(MoveNotation(coordinate.toNotationPGN(board, piece), coordinate, BishopMovement::class))
            if(targetPiece != null){
                return
            }
            this.addAvailableSquare(board, Pair(coordinate.first + xSquares, coordinate.second + ySquares), xSquares, ySquares)
        } catch (e: MovementException){
            return
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