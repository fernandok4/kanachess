package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.square.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.IPieceSpecialMovement
import br.com.kanasha.chess.models.piece.movements.KingMovement

class King(override val color: ColorPiece): IPieceSpecial {

    override val pieceMovementTypes: List<IPieceMovement> = listOf(KingMovement(this))
    override val cdPiece: String = "K"
    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
    override var isFirstMove: Boolean = true
    override val specialMovementTypes: List<IPieceSpecialMovement> = listOf()
}