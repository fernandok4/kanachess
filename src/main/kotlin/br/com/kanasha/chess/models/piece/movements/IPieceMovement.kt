package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard

interface IPieceMovement {

    fun calculateAllowedCoordinates(board: IBoard): List<Pair<Int, Int>>
}