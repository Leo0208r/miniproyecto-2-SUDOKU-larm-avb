package com.example.sudoku.controller;

import com.example.sudoku.model.SudokuBoard;
import com.example.sudoku.model.SudokuGenerator;
import com.example.sudoku.model.SudokuSolver;
import com.example.sudoku.model.SudokuValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GameController {
    private SudokuBoard sudokuBoard;
    private SudokuGenerator generator = new SudokuGenerator();
    private SudokuValidator validator = new SudokuValidator();
    private SudokuSolver solver = new SudokuSolver();
    private TextField selectedCell = null;
    private int selectedRow = -1;
    private int selectedCol = -1;
    @FXML
    private GridPane sudokuGrid;
    @FXML
    public void onHandleHelp(ActionEvent event) {



    }



    @FXML
    public void initialize(){

    }

}