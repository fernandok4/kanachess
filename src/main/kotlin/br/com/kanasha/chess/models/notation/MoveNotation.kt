package br.com.kanasha.chess.models.notation

import br.com.kanasha.chess.gson.exclusionStrategy.Exclude
import br.com.kanasha.chess.models.board.square.SquareCoordinate
import br.com.kanasha.chess.models.piece.movements.IPieceMovement
import kotlin.reflect.KClass

class MoveNotation(
    var notation: String,
    var coordinate: SquareCoordinate,
    @field:Exclude val kClass: KClass<out IPieceMovement>
)