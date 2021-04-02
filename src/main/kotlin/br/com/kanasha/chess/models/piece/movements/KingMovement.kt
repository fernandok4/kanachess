package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotationPGN
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.King
import br.com.kanasha.chess.models.piece.movements.exceptions.MovementException
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.containsCoordinate
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.isOnBoard
import br.com.kanasha.chess.models.piece.movements.utils.MovementUtils.targetAvailableSquare

class KingMovement(private val piece: IPiece): IPieceMovement {
    override fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation> {
        val possibleCoordinates = mutableListOf<MoveNotation>()
        val currentCoordinate = board.getPieceCoordinate(piece)
        currentCoordinate.getArroundSquares().forEach {
            possibleCoordinates.addAvailableSquare(board, it)
        }
        return possibleCoordinates
    }

    private fun MutableList<MoveNotation>.addAvailableSquare(board: IBoard, coordinate: Pair<Int, Int>) {
        try {
            coordinate.isOnBoard(board)
            val square = board.getSquare(coordinate.first, coordinate.second)
            val targetPiece = square.piece
            if(board.colorRound != piece.color){
                square.isUnderEnemyAttack = true
            }
            piece.targetAvailableSquare(targetPiece)
            this.containsCoordinate(coordinate)
            if(square.isUnderEnemyAttack || targetPiece?.isUnderProtection == true){
                return
            }
            val hasOppositeEnemyKing = coordinate.getArroundSquares().hasOppositeEnemyKing(board)
            if(hasOppositeEnemyKing){
                return
            }
            this.add(
                MoveNotation(
                    coordinate.toNotationPGN(board, piece),
                    coordinate,
                    KingMovement::class
                )
            )
        } catch (e: MovementException){
            return
        }
    }

    private fun Pair<Int, Int>.getArroundSquares() = listOf(Pair(this.first + 1, this.second + 1),
        Pair(this.first + 1, this.second - 1),
        Pair(this.first - 1, this.second + 1),
        Pair(this.first - 1, this.second - 1),
        Pair(this.first, this.second + 1),
        Pair(this.first, this.second - 1),
        Pair(this.first - 1, this.second),
        Pair(this.first + 1, this.second))

    private fun List<Pair<Int, Int>>.hasOppositeEnemyKing(board: IBoard) = this.any {
        try{
            it.isOnBoard(board)
            val squarePiece = board.getSquare(it.first, it.second).piece
            squarePiece is King && squarePiece.color != piece.color
        }catch (e: MovementException){
            false
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