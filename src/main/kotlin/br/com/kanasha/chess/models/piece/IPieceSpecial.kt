package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.piece.movements.IPieceSpecialMovement

interface IPieceSpecial: IPiece {

    val specialMovementTypes: List<IPieceSpecialMovement>
    var isFirstMove: Boolean
}