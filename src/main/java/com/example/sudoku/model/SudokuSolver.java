package com.example.sudoku.model;

public class SudokuSolver {
    private SudokuValidator validator= new SudokuValidator();
    public boolean solve(int[][] board){
        for (int row=0; row<6; row++){
            for (int col=0; col<6; col++){
                if (board[row][col]==0){
                    for (int num=1; num<=6; num++){
                        if (validator.isValidate(board,row,col,num)){
                            board[row][col]=num;
                            if (solve(board)){
                                return true;
                            }
                            board[row][col]=0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public int[] getHint(int[][]board){
        for (int row=0; row<6; row++){
            for (int col=0; col<6; col++){
                if(board[row][col]==0){
                    for(int num=1; num<=6; num++){
                        if(validator.isValidate(board, row,col,num)){
                            return new int[]{row, col, num};
                        }
                    }
                }
            }
        }
        return null;
    }
}
