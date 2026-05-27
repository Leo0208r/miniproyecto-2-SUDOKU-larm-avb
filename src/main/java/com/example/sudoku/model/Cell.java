package com.example.sudoku.model;

/**
 * Represents a single cell in a Sudoku board.
 * Each cell contains a numeric value (0-6) and a fixed flag indicating if it's part of the puzzle.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class Cell {
    private int value;
    private boolean fixed;

    /**
     * Constructs a Cell with an initial value and fixed status.
     *
     * @param value the initial numeric value (0 for empty, 1-6 for filled cells)
     * @param fixed true if the cell is part of the original puzzle, false if it can be modified
     */
    public Cell(int value, boolean fixed){
        this.value=value;
        this.fixed=fixed;
    }

    /**
     * Gets the numeric value of this cell.
     *
     * @return the value of the cell (0 for empty, 1-6 for filled cells)
     */
    public int getValue(){
        return value;
    }

    /**
     * Checks if this cell is fixed (part of the original puzzle).
     *
     * @return true if the cell is fixed, false otherwise
     */
    public boolean getFixed(){
        return fixed;
    }

    /**
     * Sets the numeric value of this cell.
     * Only works if the cell is not fixed.
     *
     * @param value the new numeric value (0 for empty, 1-6 for filled cells)
     */
    public void setValue(int value){
        this.value=value;
    }

    /**
     * Sets the fixed status of this cell.
     *
     * @param fixed true to mark the cell as fixed, false otherwise
     */
    public void setFixed(boolean fixed){
        this.fixed=fixed;
    }
}
