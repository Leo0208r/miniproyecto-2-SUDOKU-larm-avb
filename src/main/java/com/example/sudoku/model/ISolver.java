package com.example.sudoku.model;

/**
 * Interface for Sudoku solver operations.
 * Defines methods for solving Sudoku puzzles and providing hints.
 *
 * @author Your Name
 * @version 1.0
 */
public interface ISolver {

    /**
     * Solves the given Sudoku board using backtracking algorithm.
     *
     * @param board the 6x6 Sudoku board to solve
     * @return true if the board is solvable, false otherwise
     */
    boolean solve(int[][] board);

    /**
     * Provides a hint for an empty cell in the Sudoku board.
     *
     * @param board the current state of the 6x6 Sudoku board
     * @return an array containing [row, column, value] for the hint,
     *         or null if no hint can be provided
     */
    int[] getHint(int[][] board);
}
