package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.KnightMovement

class Knight(override val color: ColorPiece): IPiece {

    override var allowedMoves: List<MoveNotation> = listOf()
    override val pieceMovementTypes: List<IPieceMovement> = listOf(KnightMovement(this))
    override val cdPiece: String = "N"
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
}