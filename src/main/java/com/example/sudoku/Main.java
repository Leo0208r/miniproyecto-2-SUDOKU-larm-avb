package com.example.sudoku;

import com.example.sudoku.view.EndStage;
import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.MenuStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point for the Sudoku application.
 * Extends JavaFX Application and initializes the game stages and views.
 * This class launches the Sudoku game UI with menu, game, and end screens.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Main method to launch the JavaFX application.
     * Called when the program starts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and shows the primary stage for the application.
     * Sets up all game stages (menu, game, and end) and displays the main menu.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        MenuStage.setStage(primaryStage);
        GameStage.setStage(primaryStage);
        EndStage.setStage(primaryStage);
        MenuStage.showView();
    }
}