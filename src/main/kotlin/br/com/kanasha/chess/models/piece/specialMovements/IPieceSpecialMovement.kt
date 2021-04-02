package br.com.kanasha.chess.models.piece.specialMovements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.MoveNotation
import br.com.kanasha.chess.models.piece.movements.IPieceMovement

interface IPieceSpecialMovement: IPieceMovement {

    override fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation>
}