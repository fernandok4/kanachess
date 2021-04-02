package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.PawnMovement

class Pawn(override val color: ColorPiece): IPieceSpecial {

    override var allowedMoves: List<MoveNotation> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
    override var isFirstMove: Boolean = true

    override val pieceMovementTypes: List<IPieceMovement> = listOf(PawnMovement(this))
    override val cdPiece: String = ""
}