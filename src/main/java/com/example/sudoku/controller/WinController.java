package com.example.sudoku.controller;

import com.example.sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the Win/End view.
 * Handles the actions when the player wins the Sudoku game.
 */
public class WinController {

    /**
     * Handles the "Jugar" (Play) button action.
     * Starts a new game by showing the GameStage.
     *
     * @param event the action event triggered by the play button
     */
    @FXML
    public void onHandlePlayAgain(ActionEvent event) {
        GameStage.showView();
    }

    /**
     * Handles the "Salir" (Exit) button action.
     * Closes the application.
     *
     * @param event the action event triggered by the exit button
     */
    @FXML
    public void onHandleExit(ActionEvent event) {
        System.exit(0);
    }
}
