package br.com.kanasha.chess.models.board.square

import br.com.kanasha.chess.models.piece.IPiece
import com.google.gson.annotations.Expose

class Square(@Expose var piece: IPiece? = null, var isUnderEnemyAttack: Boolean = false)