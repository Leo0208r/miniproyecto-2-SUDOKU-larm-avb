package com.example.sudoku.controller;

import com.example.sudoku.model.*;
import com.example.sudoku.view.EndStage;
import com.example.sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller for the Sudoku game view.
 * Handles user interactions including cell selection, number input, and hint requests.
 *
 * @author Your Name
 * @version 1.0
 */
public class GameController {
    private SudokuBoard sudokuBoard;
    private SudokuGenerator generator = new SudokuGenerator();
    private IValidator validator = new SudokuValidator();
    private ISolver solver = new SudokuSolver();
    private TextField selectedCell = null;
    private int selectedRow = -1;
    private int selectedCol = -1;
    @FXML
    private GridPane sudokuGrid;
    /**
     * Handles the help button action.
     * Provides a hint to the player by suggesting a valid number for an empty cell.
     * The hint is displayed with a green background color.
     *
     * @param event the action event triggered by the help button
     */
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
    /**
     * Renders the entire Sudoku board in the GridPane.
     * Clears existing cells and creates new ones for all 36 positions (6x6).
     */
    private void renderBoard() {
        sudokuGrid.getChildren().clear();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField cell = createCell(row, col);
                sudokuGrid.add(cell, col, row);
            }
        }
    }

    /**
     * Creates a single cell (TextField) for the Sudoku board.
     * Fixed cells are marked as read-only with a gray background.
     * Editable cells have mouse and keyboard event handlers attached.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return a configured TextField for the Sudoku cell
     */
    private TextField createCell(int row, int col) {
        TextField cell = new TextField();
        cell.setPrefWidth(60);
        cell.setPrefHeight(60);
        cell.setAlignment(javafx.geometry.Pos.CENTER);
        int value = sudokuBoard.getValue(row, col);
        if (sudokuBoard.isFixed(row, col)) {
            cell.setText(String.valueOf(value));
            cell.setEditable(false);
            cell.setStyle("-fx-background-color: #e0e0e0;");
        } else {
            cell.setText("");
            cell.setEditable(false);
        }
        final int r = row;
        final int c = col;
        cell.setOnMouseClicked(new CellClickHandler(r, c, cell));
        cell.setOnKeyPressed(new KeyHandler(r, c, cell));
        return cell;
    }
    /**
     * Checks if the Sudoku board is completely and correctly solved.
     * If all cells are filled with valid values, displays the winning screen.
     */
    private void checkWin() {
        // Check if all cells are filled
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (sudokuBoard.getValue(row, col) == 0) {
                    return; // Board is not complete
                }
            }
        }

        // All cells are filled, so the board is solved and valid
        // (validation happens in real-time when numbers are entered)
        GameStage.deleteView();
        EndStage.showView();
    }
    /**
     * Inner class that handles mouse click events on Sudoku cells.
     * Highlights the selected cell and related cells (same row, column, or value).
     */
    private class CellClickHandler implements javafx.event.EventHandler<MouseEvent> {
        private final int row;
        private final int col;
        private final TextField cell;

        /**
         * Constructs a cell click handler for a specific cell position.
         *
         * @param row the row index
         * @param col the column index
         * @param cell the TextField representing the cell
         */
        public CellClickHandler(int row, int col, TextField cell) {
            this.row = row;
            this.col = col;
            this.cell = cell;
        }

        /**
         * Handles the mouse click event.
         * If the cell is fixed, only highlights it.
         * If the cell is editable, selects it and highlights related cells.
         *
         * @param event the mouse event
         */
        @Override
        public void handle(MouseEvent event) {
            if (sudokuBoard.isFixed(row, col)) {
                highlightBoard(row, col);
                return;
            }
            if (selectedCell != null) {
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
    /**
     * Inner class that handles keyboard input events on Sudoku cells.
     * Processes number input (1-6), delete, and backspace keys.
     * Validates entries according to Sudoku rules and highlights invalid entries.
     */
    private class KeyHandler implements javafx.event.EventHandler<KeyEvent> {
        private final int row;
        private final int col;
        private final TextField cell;

        /**
         * Constructs a key handler for a specific cell position.
         *
         * @param row the row index
         * @param col the column index
         * @param cell the TextField representing the cell
         */
        public KeyHandler(int row, int col, TextField cell) {
            this.row = row;
            this.col = col;
            this.cell = cell;
        }

        /**
         * Handles keyboard events.
         * Accepts numbers 1-6, Delete, and Backspace keys.
         * Validates entries in real-time and highlights with red border if invalid.
         * Checks for win condition after each valid entry.
         *
         * @param event the keyboard event
         */
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
                // Ignore non-numeric input
            }
        }
    }
    /**
     * Highlights cells on the board based on the clicked cell.
     * Highlights the clicked cell, its row, column, and cells with the same value.
     *
     * @param clickedRow the row index of the clicked cell
     * @param clickedCol the column index of the clicked cell
     */
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

    /**
     * Gets the base CSS style for a cell, including border styling for block separation.
     * Adds thicker borders to separate the 2x3 blocks in the 6x6 Sudoku grid.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return a CSS style string with appropriate border widths
     */
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

    /**
     * Initializes the game controller.
     * Generates a new Sudoku board and renders it to the GridPane.
     */
    @FXML
    public void initialize() {
        sudokuBoard = generator.generate();
        renderBoard();
    }


}