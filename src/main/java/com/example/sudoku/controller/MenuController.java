package com.example.sudoku.controller;
import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.MenuStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the main menu view.
 * Handles user interactions in the menu screen, such as starting a new game.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class MenuController {
    /**
     * Handles the start button action.
     * Closes the menu view and displays the game stage.
     *
     * @param event the action event triggered by the start button
     */
    @FXML
    public void onHandleStart(ActionEvent event){
        MenuStage.deleteView();
        GameStage.showView();
    }
}
