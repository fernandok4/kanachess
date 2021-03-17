package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.BishopMovement
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.RookMovement

class Queen(override val color: ColorPiece,
            override var currentNotationSquare: String = ""): IPiece {

    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
    override val pieceMovementTypes: List<IPieceMovement> = listOf(BishopMovement(this), RookMovement(this))
    override val cdPiece: String = "Q"
}