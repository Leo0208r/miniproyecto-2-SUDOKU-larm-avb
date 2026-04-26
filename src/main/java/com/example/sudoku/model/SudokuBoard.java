package com.example.sudoku.model;

public class SudokuBoard {
    private static final int size=6;
    private int[][] board;
    private boolean[][] fixed;

    public SudokuBoard(){
        board= new int[size][size];
        fixed= new boolean[size][size];
    }
    public int getValue(int row, int column){
        return board[row][column];
    }
    public void setValue(int row,int column, int value){
        if(!fixed[row][column]){
            board[row][column]=value;
        }
    }
    public boolean isFixed(int row, int column){
        return fixed[row][column];
    }
    public void setFixed(int row, int column, boolean value){
        fixed[row][column]= value;
    }
    public int[][] getBoard(){
        return board;
    }
    public void setBoard(int[][] board){
        this.board=board;
    }



}
