package br.com.kanasha.chess.service

import br.com.kanasha.chess.models.Board
import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.notation.ChessNotationRead.toNotation
import br.com.kanasha.chess.models.piece.Bishop
import br.com.kanasha.chess.models.piece.IPiece
import br.com.kanasha.chess.models.piece.Queen
import br.com.kanasha.chess.models.piece.Rook

class DiscoverAllowedMovementsService(private val board: Board) {

    fun discover(){
        discoverAvailablePieceCoordinates()
        discoverAvailableMoveNotations()
    }

    private fun discoverAvailableMoveNotations(){
        board.squares.forEach {
            it.forEach { square ->
                square.piece?.let {
                    if(it is Rook || it is Bishop || it is Queen){
                        discoverPieceMovements(it)
                    }
                }
            }
        }
    }

    private fun discoverAvailablePieceCoordinates(){
        board.squares.forEach {
            it.forEach { square ->
                square.piece?.let {
                    if(it is Rook || it is Bishop || it is Queen){
                        discoverPieceCoordinates(it)
                    }
                }
            }
        }
    }

    private fun discoverPieceCoordinates(piece: IPiece) {
        val allowedCoordinates = mutableListOf<Pair<Int, Int>>()
        for(moveType in piece.pieceMovementTypes){
            allowedCoordinates.addAll(moveType.calculateAllowedCoordinates(board))
        }
        piece.allowedCoordinates = allowedCoordinates
    }

    private fun discoverPieceMovements(piece: IPiece) {
        val allowedCoordinates = piece.allowedCoordinates.map { SquareCoordanate(it, it.toNotation(board, piece)) }
        piece.allowedMoves = allowedCoordinates
    }
}