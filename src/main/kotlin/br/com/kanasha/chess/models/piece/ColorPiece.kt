package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.square.SquareCoordinate

enum class ColorPiece(val factorSide: SquareCoordinate) {
    BLACK(SquareCoordinate(0, -1)), WHITE(SquareCoordinate(0, 1));
}