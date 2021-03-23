package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.square.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.IPieceSpecialMovement
import br.com.kanasha.chess.models.piece.movements.RookMovement

class Rook(override val color: ColorPiece): IPieceSpecial {

    override val pieceMovementTypes: List<IPieceMovement> = listOf(RookMovement(this))
    override val cdPiece: String = "R"
    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
    override var isFirstMove: Boolean = true
    override val specialMovementTypes: List<IPieceSpecialMovement> = listOf()
}