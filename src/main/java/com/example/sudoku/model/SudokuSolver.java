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
    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board.getValue(row, col) == 0) {
                    for (int num = 1; num <= 6; num++) {
                        if (validator.isValidate(board, row, col, num)) {
                            board.setValue(row,col,num);
                            if (solve(board)) {
                                return true;
                            }
                            board.setValue(row, col,0);
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
    public int[] getHint(SudokuBoard board) {
        SudokuBoard copy = new SudokuBoard();
        if (!solve(copy)) return null;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board.getValue(row,col) == 0) {
                    return new int[]{row, col, copy.getValue(row,col)};
                }
            }
        }
        return null;
    }
    private SudokuBoard copyBoard(SudokuBoard board) {
        SudokuBoard copy = new SudokuBoard();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // copy does not set fixed so solver can overwrite freely
                copy.setValue(row, col, board.getValue(row, col));
            }
        }
        return copy;
    }
}
