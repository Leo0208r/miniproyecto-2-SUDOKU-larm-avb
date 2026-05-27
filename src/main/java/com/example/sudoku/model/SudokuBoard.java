package com.example.sudoku.model;

/**
 * Represents the Sudoku board using a tree data structure.
 * Manages a 6x6 grid of cells organized hierarchically with a root node,
 * row nodes, and individual cell nodes.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class SudokuBoard {
    private static final int SIZE=6;
    private TreeNode root;

    /**
     * Constructs a new SudokuBoard with a 6x6 grid.
     * Initializes all cells as empty (value 0) and not fixed.
     */
    public SudokuBoard(){
        root=new TreeNode(-1, -1, null);
        for (int row=0; row<6; row++){
            TreeNode rowNode= new TreeNode(row, -1,null);
            for (int col=0; col<6; col++){
                TreeNode cellNode=new TreeNode(row,col,new Cell(0,false));
                rowNode.addChild(cellNode);
            }
            root.addChild(rowNode);
        }
    }

    /**
     * Retrieves the TreeNode at the specified row and column.
     *
     * @param row the row index
     * @param col the column index
     * @return the TreeNode containing the cell at this position
     */
    private TreeNode getNode(int row, int col){
        TreeNode rowNode=root.getChildren().get(row);
        return rowNode.getChildren().get(col);

    }

    /**
     * Gets the value at the specified position.
     *
     * @param row the row index
     * @param column the column index
     * @return the numeric value at this position (0 for empty, 1-6 for filled)
     */
    public int getValue(int row, int column){
        return getNode(row,column).getCell().getValue();
    }

    /**
     * Sets the value at the specified position.
     * Only allows setting if the cell is not fixed.
     *
     * @param row the row index
     * @param column the column index
     * @param value the numeric value to set (0 for empty, 1-6 for filled)
     */
    public void setValue(int row,int column, int value){
        Cell cell=getNode(row,column).getCell();
        if(!cell.getFixed()){
            cell.setValue(value);
        }
    }

    /**
     * Checks if the cell at the specified position is fixed.
     *
     * @param row the row index
     * @param column the column index
     * @return true if the cell is fixed, false otherwise
     */
    public boolean isFixed(int row, int column){
        return getNode(row, column).getCell().getFixed();
    }

    /**
     * Sets the fixed status of the cell at the specified position.
     *
     * @param row the row index
     * @param column the column index
     * @param value true to mark as fixed, false otherwise
     */
    public void setFixed(int row, int column, boolean value){
        getNode(row,column).getCell().setFixed(value);
    }

    /**
     * Gets the size of the Sudoku board.
     *
     * @return the board size (6 for a 6x6 board)
     */
    public int getSize(){
        return SIZE;
    }

}
