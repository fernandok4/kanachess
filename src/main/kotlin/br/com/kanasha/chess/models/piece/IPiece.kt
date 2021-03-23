package br.com.kanasha.chess.models.piece

import br.com.kanasha.chess.models.board.square.SquareCoordanate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement

interface IPiece {

    val cdPiece: String                          // Código da peça de acordo com PGN
    val color: ColorPiece
    val pieceMovementTypes: List<IPieceMovement> // Tipos de movimentos das peças
    var allowedMoves: List<SquareCoordanate>           // Coordenadas disponiveis para atacar mas em formato PGN
    var isUnderProtection: Boolean
    var isUnderAttack: Boolean
    var isDead: Boolean

    fun protect(otherPiece: IPiece) {
        otherPiece.isUnderProtection = true
    }

    fun attack(otherPiece: IPiece) {
        otherPiece.isUnderAttack = true
    }

    fun kill(otherPiece: IPiece){
        otherPiece.isDead = true
    }

    fun resetPieceStatus(){
        this.isUnderAttack = false
        this.isUnderProtection = false
    }
}