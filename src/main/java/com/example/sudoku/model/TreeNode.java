package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in the tree structure used to organize the Sudoku board.
 * Each node can have multiple children and represents either a row or a cell.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class TreeNode {
    private Cell cell;
    private int row;
    private int col;
    private List<TreeNode> children;

    /**
     * Constructs a TreeNode with position information and an optional cell.
     *
     * @param row the row index (-1 for non-cell nodes)
     * @param col the column index (-1 for non-cell nodes)
     * @param cell the Cell object (null for non-cell nodes)
     */
    public TreeNode(int row,int  col, Cell cell){
        this.row=row;
        this.col=col;
        this.cell=cell;
        this.children= new ArrayList<>();
    }

    /**
     * Adds a child node to this node.
     *
     * @param child the TreeNode to add as a child
     */
    public void addChild(TreeNode child){
        children.add(child);
    }

    /**
     * Gets the list of children of this node.
     *
     * @return the list of child TreeNodes
     */
    public List<TreeNode> getChildren(){
        return children;
    }

    /**
     * Gets the row index of this node.
     *
     * @return the row index
     */
    public int getRow(){
        return row;
    }

    /**
     * Gets the column index of this node.
     *
     * @return the column index
     */
    public int getCol(){
        return col;
    }

    /**
     * Gets the Cell object associated with this node.
     *
     * @return the Cell object, or null if this node doesn't represent a cell
     */
    public Cell getCell(){
        return  cell;
    }
}
