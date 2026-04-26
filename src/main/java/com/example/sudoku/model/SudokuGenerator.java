package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {
    private SudokuSolver solver= new SudokuSolver();
    private SudokuValidator validator= new SudokuValidator();

    public SudokuBoard generate(){
        int[][] board= new int[6][6];
        fillBoard(board);
        int[][] solution= copyBoard(board);
        removeNumbers(board);
        SudokuBoard sudokuBoard= new SudokuBoard();
        sudokuBoard.setBoard(board);
        markFixedCells(sudokuBoard);
        return sudokuBoard;

    }
    private boolean fillBoard(int[][] board){
        for(int row=0; row<6; row++){
            for(int col=0; col<6; col++){
                if (board[row][col]==0){
                    List<Integer> numbers=getShuffledNumbers();
                    for (int num: numbers){
                        if(validator.isValidate(board,row,col,num)){
                            board[row][col]=num;
                            if (fillBoard(board)){
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
    private List<Integer> getShuffledNumbers(){
        List<Integer> numbers= new ArrayList<>();
        for (int i=1; i<=6; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }
    private void removeNumbers(int[][] board){
        for (int blocRow=0; blocRow<3;blocRow++){
            for (int blocCol=0; blocCol<2; blocCol++){
                removeFromBlock(board, blocRow*2, blocCol*3);
            }
        }
    }
    private void removeFromBlock(int[][]board, int startRow, int startCol){
        List<int[]> positions= new ArrayList<>();
        for (int r=startRow; r<startRow+2; r++){
            for (int c= startCol; c<startCol+3; c++ ){
                positions.add(new int[]{r,c});
            }
        }
        Collections.shuffle(positions);
        for (int i=2; i<positions.size(); i++){
            int[] pos=positions.get(i);
            board[pos[0]][pos[1]]=0;
        }
    }
    private void markFixedCells(SudokuBoard sudokuBoard){
        for (int row=0; row<6;row++){
            for (int col=0; col<6; col++){
                if (sudokuBoard.getValue(row,col)!=0){
                    sudokuBoard.setFixed(row, col,true);
                }
            }
        }
    }
    private int[][] copyBoard(int[][] board){
        int[][] copy= new int[6][6];
        for (int row=0; row<6; row++){
            for(int col=0; col<6; col++){
                copy[row][col]=board[row][col];
            }
        }
        return copy;
    }
}
