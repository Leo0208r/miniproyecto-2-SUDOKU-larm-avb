package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the game stage/window for the Sudoku application.
 * Handles loading and displaying the game view from FXML.
 * This is a utility class with static methods to manage the game stage.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class GameStage {
    private static Stage gameStage;
    private static final String FXML_PATH= "/com/example/sudoku/Game-view.fxml";

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class.
     */
    private GameStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}

    /**
     * Sets the primary stage for the game view.
     *
     * @param stage the Stage to use for displaying the game
     */
    public static void setStage(Stage stage){gameStage=stage;}

    /**
     * Loads and displays the game view.
     * Sets up the scene, title, and icon for the game stage.
     */
    public static void showView() {
        FXMLLoader loader= new FXMLLoader(
                MenuStage.class.getResource(FXML_PATH)
        );
        Parent root;
        try{
            root=loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(root);
        gameStage.setScene(scene);
        gameStage.setTitle("Sudoku");
        gameStage.getIcons().add(new Image(
                String.valueOf(MenuStage.class.getResource("/com/example/sudoku/Icons/jugando-videojuegos.png"))
        ));
        gameStage.show();
    }

    /**
     * Closes the game view/stage.
     */
    public static void deleteView() {
        gameStage.close();
    }
}
