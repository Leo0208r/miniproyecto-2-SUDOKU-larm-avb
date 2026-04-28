package com.example.sudoku.model;

/**
 * Interface for Sudoku validation operations.
 * Defines methods for validating Sudoku board entries.
 *
 * @author Your Name
 * @version 1.0
 */
public interface IValidator {

    /**
     * Validates if a value can be placed at the specified position in the Sudoku board.
     * Checks if the value violates any Sudoku rules (row, column, or 2x3 block).
     *
     * @param board the 6x6 Sudoku board
     * @param row the row index
     * @param column the column index
     * @param value the value to validate (1-6)
     * @return true if the value is valid for this position, false otherwise
     */
    boolean isValidate(int[][] board, int row, int column, int value);

    /**
     * Checks if a value already exists in the specified row.
     *
     * @param board the 6x6 Sudoku board
     * @param row the row index
     * @param value the value to check (1-6)
     * @return true if the value exists in the row, false otherwise
     */
    boolean isRow(int[][] board, int row, int value);

    /**
     * Checks if a value already exists in the specified column.
     *
     * @param board the 6x6 Sudoku board
     * @param column the column index
     * @param value the value to check (1-6)
     * @return true if the value exists in the column, false otherwise
     */
    boolean isColumn(int[][] board, int column, int value);

    /**
     * Checks if a value already exists in the 2x3 block containing the specified position.
     *
     * @param board the 6x6 Sudoku board
     * @param row the row index
     * @param column the column index
     * @param value the value to check (1-6)
     * @return true if the value exists in the block, false otherwise
     */
    boolean isBlock(int[][] board, int row, int column, int value);
}
