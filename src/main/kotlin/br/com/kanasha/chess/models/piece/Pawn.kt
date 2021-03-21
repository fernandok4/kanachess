package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.IPieceSpecialMovement
import br.com.kanasha.chess.models.piece.movements.PawnMovement

class Pawn(override val color: ColorPiece): IPieceSpecial {

    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
    override var isFirstMove: Boolean = true

    override val pieceMovementTypes: List<IPieceMovement> = listOf(PawnMovement(this))
    override val specialMovementTypes: List<IPieceSpecialMovement> = listOf()
    override val cdPiece: String = ""
}