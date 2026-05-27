package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Cell cell;
    private int row;
    private int col;
    private List<TreeNode> children;
    public TreeNode(int row,int  col, Cell cell){
        this.row=row;
        this.col=col;
        this.cell=cell;
        this.children= new ArrayList<>();
    }
    public void addChild(TreeNode child){
        children.add(child);
    }
    public List<TreeNode> getChildren(){
        return children;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public Cell getCell(){
        return  cell;
    }
}
