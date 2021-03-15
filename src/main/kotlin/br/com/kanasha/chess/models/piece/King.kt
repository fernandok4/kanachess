package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import br.com.kanasha.chess.models.piece.movements.KingMovement

class King(override val color: ColorEnum,
           override var currentNotationSquare: String = ""): IPiece {

    override var allowedMoves: List<SquareCoordanate> = listOf()
    override var allowedCoordinates: List<Pair<Int, Int>> = listOf()
    override val pieceMovementTypes: List<IPieceMovement> = listOf(KingMovement(this))

    override val cdPiece: String = "K"
    override var isFirstMove: Boolean = true
    override var isUnderProtection: Boolean = false
    override var isUnderAttack: Boolean = false
    override var isDead: Boolean = false
}