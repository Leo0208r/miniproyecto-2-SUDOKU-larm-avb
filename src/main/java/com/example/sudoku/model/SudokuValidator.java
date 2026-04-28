package com.example.sudoku.model;

/**
 * Implementation of the Sudoku validator that checks if moves are valid.
 * Validates entries based on Sudoku rules for 6x6 boards with 2x3 blocks.
 *
 * @author Your Name
 * @version 1.0
 */
public class SudokuValidator implements IValidator {

    /**
     * Validates if a value can be placed at the specified position.
     * A value is valid if it doesn't appear in the row, column, or 2x3 block.
     *
     * @param board the 6x6 Sudoku board
     * @param row the row index
     * @param column the column index
     * @param value the value to validate (1-6)
     * @return true if the value is valid for this position, false otherwise
     */
    @Override
    public boolean isValidate(int[][] board, int row, int column, int value) {
        return !isRow(board, row, value) && !isColumn(board, column, value) && !isBlock(board, row, column, value);
    }

    /**
     * Checks if a value already exists in the specified row.
     *
     * @param board the 6x6 Sudoku board
     * @param row the row index
     * @param value the value to check (1-6)
     * @return true if the value exists in the row, false otherwise
     */
    @Override
    public boolean isRow(int[][] board, int row, int value) {
        for (int col = 0; col < 6; col++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value already exists in the specified column.
     *
     * @param board the 6x6 Sudoku board
     * @param column the column index
     * @param value the value to check (1-6)
     * @return true if the value exists in the column, false otherwise
     */
    @Override
    public boolean isColumn(int[][] board, int column, int value) {
        for (int row = 0; row < 6; row++) {
            if (board[row][column] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value already exists in the 2x3 block containing the specified position.
     * For a 6x6 board, blocks are arranged in 3 rows and 2 columns of 2x3 cells.
     *
     * @param board the 6x6 Sudoku board
     * @param row the row index
     * @param column the column index
     * @param value the value to check (1-6)
     * @return true if the value exists in the block, false otherwise
     */
    @Override
    public boolean isBlock(int[][] board, int row, int column, int value) {
        int blockRowStart = (row / 2) * 2;
        int blockColStart = (column / 3) * 3;
        for (int r = blockRowStart; r < blockRowStart + 2; r++) {
            for (int c = blockColStart; c < blockColStart + 3; c++) {
                if (board[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }

}
