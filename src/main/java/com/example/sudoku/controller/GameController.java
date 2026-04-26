package com.example.sudoku.controller;

import com.example.sudoku.model.SudokuBoard;
import com.example.sudoku.model.SudokuGenerator;
import com.example.sudoku.model.SudokuSolver;
import com.example.sudoku.model.SudokuValidator;
import com.example.sudoku.view.EndStage;
import com.example.sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
        int[] hint = solver.getHint(sudokuBoard.getBoard());

        if (hint == null) return;

        int hintRow = hint[0];
        int hintCol = hint[1];
        int hintValue = hint[2];

        for (javafx.scene.Node node : sudokuGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == hintRow
                    && GridPane.getColumnIndex(node) == hintCol) {
                TextField cell = (TextField) node;
                sudokuBoard.setValue(hintRow, hintCol, hintValue);
                cell.setText(String.valueOf(hintValue));
                cell.setStyle("-fx-background-color: #d4edda;");
                break;
            }
        }
    }
    private void renderBoard(){
        sudokuGrid.getChildren().clear();
        for (int row=0; row<6; row++){
            for (int col=0; col<6; col++){
                TextField cell= createCell(row,col);
                sudokuGrid.add(cell, col, row);
            }
        }
    }
    private TextField createCell(int row, int col){
        TextField cell = new TextField();
        cell.setPrefWidth(60);
        cell.setPrefHeight(60);
        cell.setAlignment(javafx.geometry.Pos.CENTER);
        int value= sudokuBoard.getValue(row, col);
        if (sudokuBoard.isFixed(row, col)){
            cell.setText(String.valueOf(value));
            cell.setEditable(false);
            cell.setStyle("-fx-background-color: #e0e0e0;");
        }else{
            cell.setText("");
            cell.setEditable(false);
        }
        final int r= row;
        final int c= col;
        cell.setOnMouseClicked(new CellClickHandler(r, c, cell));
        cell.setOnKeyPressed(new KeyHandler(r, c, cell));
        return cell;

    }
    private void checkWin() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int value = sudokuBoard.getValue(row, col);
                if (value == 0) return;
                sudokuBoard.setValue(row, col, 0);
                boolean valid = validator.isValidate(
                        sudokuBoard.getBoard(), row, col, value
                );
                sudokuBoard.setValue(row, col, value);
                if (!valid) return;
            }
        }
        GameStage.deleteView();
        EndStage.showView();
    }
    private class CellClickHandler implements javafx.event.EventHandler<MouseEvent>{
        private final int row;
        private final int col;
        private final TextField cell;
        public CellClickHandler(int row, int col, TextField cell){
            this.row=row;
            this.col=col;
            this.cell=cell;
        }
        @Override
        public void handle(MouseEvent event) {
            if (sudokuBoard.isFixed(row,col)){
                highlightBoard(row,col);
                return;
            }
            if(selectedCell != null){
                selectedCell.setStyle("");
            }
            selectedCell = cell;
            selectedRow = row;
            selectedCol = col;
            cell.setStyle("-fx-background-color: #cce5ff;");
            highlightBoard(row, col);
            cell.requestFocus();
        }
    }
    private class KeyHandler implements javafx.event.EventHandler<KeyEvent>{
        private final int row;
        private final int col;
        private final TextField cell;
        public KeyHandler(int row, int col, TextField cell){
            this.row=row;
            this.col=col;
            this.cell=cell;
        }
        @Override
        public void handle(KeyEvent event) {
            if (sudokuBoard.isFixed(row, col)) return;
            String key = event.getCode().toString();
            if (key.equals("BACK_SPACE") || key.equals("DELETE")) {
                sudokuBoard.setValue(row, col, 0);
                cell.setText("");
                cell.setStyle("-fx-background-color: #cce5ff;");
                return;
            }
            try {
                int num = Integer.parseInt(event.getText());
                if (num >= 1 && num <= 6) {
                    if (validator.isValidate(sudokuBoard.getBoard(), row, col, num)) {
                        sudokuBoard.setValue(row, col, num);
                        cell.setText(String.valueOf(num));
                        cell.setStyle("-fx-background-color: #cce5ff;");
                        checkWin();
                    } else {
                        cell.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
    }
    private void highlightBoard(int clickedRow, int clickedCol) {
        int clickedValue = sudokuBoard.getValue(clickedRow, clickedCol);

        for (javafx.scene.Node node : sudokuGrid.getChildren()) {
            int r = GridPane.getRowIndex(node);
            int c = GridPane.getColumnIndex(node);
            TextField cell = (TextField) node;

            if (r == clickedRow && c == clickedCol) {
                cell.setStyle(getBaseStyle(r, c) + "-fx-background-color: #f0a500;");

            } else if (r == clickedRow || c == clickedCol) {
                cell.setStyle(getBaseStyle(r, c) + "-fx-background-color: #cce5ff;");

            } else if (clickedValue != 0
                    && sudokuBoard.getValue(r, c) == clickedValue) {
                cell.setStyle(getBaseStyle(r, c) + "-fx-background-color: #cce5ff;");

            } else {
                if (sudokuBoard.isFixed(r, c)) {
                    cell.setStyle(getBaseStyle(r, c) + "-fx-background-color: #e0e0e0;");
                } else {
                    cell.setStyle(getBaseStyle(r, c) + "-fx-background-color: white;");
                }
            }
        }
    }
    private String getBaseStyle(int row, int col) {
        String borderTop    = "1px";
        String borderRight  = "1px";
        String borderBottom = "1px";
        String borderLeft   = "1px";
        if (row == 1 || row == 3) borderBottom = "3px";
        if (col == 2) borderRight = "3px";
        return "-fx-border-color: #aaaaaa;"
                + "-fx-border-width: "
                + borderTop + " " + borderRight + " " + borderBottom + " " + borderLeft + ";";
    }
    @FXML
    public void initialize(){
        sudokuBoard=generator.generate();
        renderBoard();
    }


}