package br.com.kanasha.chess.models.board

import br.com.kanasha.chess.models.piece.IPiece

class Square(var piece: IPiece? = null, var isUnderEnemyAttack: Boolean = false)