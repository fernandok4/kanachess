package br.com.kanasha.chess.models.board.square

class SquareCoordinate(val x: Int, val y: Int){
    override fun equals(other: Any?): Boolean {
        if(other is SquareCoordinate){
            return x == other.x && y == other.y
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}