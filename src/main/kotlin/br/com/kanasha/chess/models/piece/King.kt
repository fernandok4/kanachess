package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.KingMovement
import br.com.kanasha.chess.models.piece.specialMovements.CastlingMovement

class King(override val color: ColorPiece): IPieceSpecial {

    override val pieceMovementTypes: List<IPieceMovement> = listOf(KingMovement(this), CastlingMovement(this))
    override val cdPiece: String = "K"
    override var allowedMoves: List<MoveNotation> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
    override var isFirstMove: Boolean = true
}