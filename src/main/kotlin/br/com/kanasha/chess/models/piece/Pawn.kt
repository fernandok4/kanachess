package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.PawnMovement

class Pawn(override val color: ColorEnum,
           override var currentNotationSquare: String = ""): IPiece {

    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var allowedCoordinates: List<Pair<Int, Int>> = listOf()
    override val pieceMovementTypes: List<IPieceMovement> = listOf(PawnMovement(this))
    override var isFirstMove: Boolean = true

    override val cdPiece: String = ""
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
}