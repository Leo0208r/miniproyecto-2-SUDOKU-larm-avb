package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates random valid Sudoku puzzles.
 * Uses a backtracking algorithm to create complete boards and then removes numbers to create puzzles.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class SudokuGenerator {
    private SudokuSolver solver= new SudokuSolver();
    private SudokuValidator validator= new SudokuValidator();

    /**
     * Generates a new random Sudoku puzzle.
     * Creates a complete valid board, removes some numbers to create a puzzle,
     * and marks the remaining numbers as fixed.
     *
     * @return a new SudokuBoard with a random puzzle
     */
    public SudokuBoard generate(){
        //int[][] board= new int[6][6];
        SudokuBoard board= new SudokuBoard();
        fillBoard(board);
        removeNumbers(board);
        markFixedCells(board);
        return board;

    }

    /**
     * Recursively fills the board with valid numbers using backtracking.
     *
     * @param board the board to fill
     * @return true if the board was successfully filled, false otherwise
     */
    private boolean fillBoard(SudokuBoard board){
        for(int row=0; row<6; row++){
            for(int col=0; col<6; col++){
                if (board.getValue(row,col)==0){
                    List<Integer> numbers=getShuffledNumbers();
                    for (int num: numbers){
                        if(validator.isValidate(board,row,col,num)){
                            board.setValue(row,col,num);
                            if (fillBoard(board)){
                                return true;
                            }
                            board.setValue(row,col,0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets a shuffled list of numbers from 1 to 6.
     *
     * @return a shuffled list of valid Sudoku numbers
     */
    private List<Integer> getShuffledNumbers(){
        List<Integer> numbers= new ArrayList<>();
        for (int i=1; i<=6; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    /**
     * Removes numbers from the board by iterating through 2x3 blocks.
     *
     * @param board the board from which to remove numbers
     */
    private void removeNumbers(SudokuBoard board){
        for (int blocRow=0; blocRow<3;blocRow++){
            for (int blocCol=0; blocCol<2; blocCol++){
                removeFromBlock(board, blocRow*2, blocCol*3);
            }
        }
    }

    /**
     * Removes numbers from a specific 2x3 block, keeping only 2 numbers.
     *
     * @param board the board to modify
     * @param startRow the starting row of the block
     * @param startCol the starting column of the block
     */
    private void removeFromBlock(SudokuBoard board, int startRow, int startCol){
        List<int[]> positions= new ArrayList<>();
        for (int r=startRow; r<startRow+2; r++){
            for (int c= startCol; c<startCol+3; c++ ){
                positions.add(new int[]{r,c});
            }
        }
        Collections.shuffle(positions);
        for (int i=2; i<positions.size(); i++){
            int[] pos=positions.get(i);
            board.setValue(pos[0],pos[1],0);
        }
    }

    /**
     * Marks all non-zero cells as fixed.
     *
     * @param sudokuBoard the board where cells should be marked as fixed
     */
    private void markFixedCells(SudokuBoard sudokuBoard){
        for (int row=0; row<6;row++){
            for (int col=0; col<6; col++){
                if (sudokuBoard.getValue(row,col)!=0){
                    sudokuBoard.setFixed(row, col,true);
                }
            }
        }
    }

}
