package com.seregil13.sudoku

data class SudokuBoard(val board: Array<CharArray>, val placeholder: Char = '-') {
    init {
        require(board.size == 9) { "Not enough rows in the board" }
        board.forEachIndexed { index, chars ->
            require(chars.size == 9) { "Not enough columns in row $index" }
        }
    }

    private val validValues = charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9')

    private fun put(cell: Cell, value: Char) {
        board[cell.row][cell.col] = value
    }

    fun print() {
        println()
        board.forEach {
            print("| ")
            it.forEach { c -> print("$c ") }
            println("|")
        }
        println()
    }

    private fun reset(cell: Cell) {
        board[cell.row][cell.col] = placeholder
    }

    private fun findPlaceholder(): Cell? {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (get(i, j) == placeholder) return Cell(i, j)
            }
        }

        return null
    }

    fun solve(): Boolean {
        if (!isValid()) return false
        val cell: Cell = findPlaceholder() ?: return true

        for (i in 0 until 9) {
            put(cell, validValues[i])
            if (solve()) return true
            else reset(cell)
        }

        return false
    }

    fun get(row: Int, col: Int): Char = board[row][col]

    private fun isValid(): Boolean {
        val set = hashSetOf<String>()
        for (i in board.indices) {
            for (j in board.indices) {
                if (board[i][j] != placeholder) {
                    val row = "row${i}-${board[i][j]}"
                    val col = "col${j}-${board[i][j]}"
                    val mat = "mat${i/3}-${j/3}-${board[i][j]}"

                    if (!set.add(row) || !set.add(col) || !set.add(mat))
                        return false
                }
            }
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SudokuBoard

        if (!board.contentDeepEquals(other.board)) return false
        if (placeholder != other.placeholder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = board.contentDeepHashCode()
        result = 31 * result + placeholder.hashCode()
        return result
    }
}







