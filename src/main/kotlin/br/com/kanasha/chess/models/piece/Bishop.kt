package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.movements.BishopMovement
import br.com.kanasha.chess.models.piece.movements.IPieceMovement

class Bishop(override val color: ColorPiece): IPiece {

    override val pieceMovementTypes: List<IPieceMovement> = listOf(BishopMovement(this))
    override val cdPiece: String = "B"
    override var allowedMoves: List<MoveNotation> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
}