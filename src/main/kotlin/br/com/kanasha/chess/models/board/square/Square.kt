package br.com.kanasha.chess.models.board.square

import br.com.kanasha.chess.models.piece.IPiece

class Square(var piece: IPiece? = null, var isUnderEnemyAttack: Boolean = false)