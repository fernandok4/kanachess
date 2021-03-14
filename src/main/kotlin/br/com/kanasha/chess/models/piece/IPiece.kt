package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement

interface IPiece {

    val cdPiece: String                          // Código da peça de acordo com PGN
    val color: ColorEnum
    var currentNotationSquare: String            // Posição atual do tabuleiro usando notação PGN
    var allowedCoordinates: List<Pair<Int, Int>> // Coordenadas disponíveis para atacar sendo o first = X e second = Y
    var allowedMoves: List<SquareCoordanate>           // Coordenadas disponiveis para atacar mas em formato PGN
    val pieceMovementTypes: List<IPieceMovement> // Tipos de movimentos das peças
}