package com.example.sudoku.model;

public class SudokuBoard {
    private static final int SIZE=6;
    private TreeNode root;

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
    private TreeNode getNode(int row, int col){
        TreeNode rowNode=root.getChildren().get(row);
        return rowNode.getChildren().get(col);

    }
    public int getValue(int row, int column){
        return getNode(row,column).getCell().getValue();
    }
    public void setValue(int row,int column, int value){
        Cell cell=getNode(row,column).getCell();
        if(!cell.getFixed()){
            cell.setValue(value);
        }
    }
    public boolean isFixed(int row, int column){
        return getNode(row, column).getCell().getFixed();
    }
    public void setFixed(int row, int column, boolean value){
        getNode(row,column).getCell().setFixed(value);
    }
    public int getSize(){
        return SIZE;
    }



}
