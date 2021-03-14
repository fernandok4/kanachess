package br.com.kanasha.chess.models.piece.movements.utils

object MovementUtils {

    val boardSquareSize = 0..7

    fun Pair<Int, Int>.isOnBoard() = this.first in boardSquareSize && this.second in boardSquareSize
}