package com.example.sudoku.model;

public class SudokuValidator {
    public boolean isValidate (int[][]board, int row, int column, int value){
        return !isRow(board,row,value)&&!isColumn(board, column, value) && !isBlock(board, row, column, value);
    }
    public boolean isRow(int[][] board, int row, int value){
        for(int col=0; col<6; col++){
            if(board[row][col]==value){
                return true;
            }
        }
        return false;
    }
    public boolean isColumn(int[][] board, int column, int value){
        for(int row=0; row<6; row++){
            if (board[row][column]==value){
                return true;
            }
        }
        return false;
    }
    public boolean isBlock(int[][]board, int row, int column,int value){
        int blockRowStart= (row/2)*2;
        int blockColStart= (column/3)*3;
        for (int r=blockRowStart; r<blockRowStart+2;r++){
            for (int c=blockColStart; c<blockColStart+3; c++){
                if(board[r][c]== value){
                    return true;
                }
            }
        }
        return false;
    }

}
