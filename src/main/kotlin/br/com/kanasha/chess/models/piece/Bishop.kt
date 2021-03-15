package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.BishopMovement
import br.com.kanasha.chess.models.piece.movements.IPieceMovement

class Bishop(override val color: ColorEnum,
             override var currentNotationSquare: String = ""): IPiece {

    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var allowedCoordinates: List<Pair<Int, Int>> = listOf()
    override val pieceMovementTypes: List<IPieceMovement> = listOf(BishopMovement(this))

    override val cdPiece: String = "B"
    override var isFirstMove: Boolean = true
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
}