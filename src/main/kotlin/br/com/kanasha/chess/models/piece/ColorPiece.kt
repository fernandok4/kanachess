package br.com.kanasha.chess.models.piece

enum class ColorPiece(val factorSide: Pair<Int, Int>) {
    BLACK(Pair(0, -1)), WHITE(Pair(0, 1));
}