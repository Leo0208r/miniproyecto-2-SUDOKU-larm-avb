package com.example.sudoku.model;

/**
 * Implementation of the Sudoku solver using backtracking algorithm.
 * Provides methods to solve puzzles and generate hints.
 *
 * @author Your Name
 * @version 1.0
 */
public class SudokuSolver implements ISolver {
    private IValidator validator = new SudokuValidator();

    /**
     * Solves the given Sudoku board using a recursive backtracking algorithm.
     *
     * @param board the 6x6 Sudoku board to solve
     * @return true if the board is solvable, false otherwise
     */
    @Override
    public boolean solve(int[][] board) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 6; num++) {
                        if (validator.isValidate(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Provides a hint for an empty cell in the Sudoku board.
     * Solves a copy of the board and returns the value for the first empty cell found.
     *
     * @param board the current state of the 6x6 Sudoku board
     * @return an array containing [row, column, value] for the hint,
     *         or null if no hint can be provided (board is unsolvable)
     */
    @Override
    public int[] getHint(int[][] board) {
        int[][] copy = new int[6][6];
        for (int i = 0; i < 6; i++) {
            copy[i] = board[i].clone();
        }
        if (!solve(copy)) return null;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col, copy[row][col]};
                }
            }
        }
        return null;
    }
}
