package br.com.kanasha.chess.models.piece.movements

import br.com.kanasha.chess.models.board.IBoard
import br.com.kanasha.chess.models.notation.MoveNotation

interface IPieceMovement {

    fun calculateAllowedCoordinates(board: IBoard): List<MoveNotation>

    fun doMovement(board: IBoard, stringNotation: String)
}