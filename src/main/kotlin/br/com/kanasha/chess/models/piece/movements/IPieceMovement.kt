package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.Board

interface IPieceMovement {

    fun calculateAllowedCoordinates(board: Board): List<Pair<Int, Int>>
}