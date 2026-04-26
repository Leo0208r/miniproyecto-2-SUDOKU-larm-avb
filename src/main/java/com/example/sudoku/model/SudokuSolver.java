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
    public int[] getHint(int[][] board) {
        int[][] copy = new int[6][6];
        for (int i = 0; i < 6; i++) {
            copy[i] = board[i].clone();
        }
        if (!solve(copy)) return null;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col, copy[row][col]};
                }
            }
        }
        return null;
    }
}
