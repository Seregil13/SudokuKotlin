package com.seregil13

import com.seregil13.sudoku.*

fun main() {
    val test1 = SudokuBoard(testInput1)
    val test2 = SudokuBoard(testInput2)
    val test3 = SudokuBoard(testInput3)

    test1.solve()
    test2.solve()
    test3.solve()

    test1.print()
    test2.print()
    test3.print()
}
