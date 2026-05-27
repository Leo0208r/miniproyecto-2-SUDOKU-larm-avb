package com.example.sudoku.view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the end/victory stage for the Sudoku application.
 * Handles loading and displaying the end screen when the player wins.
 * This is a utility class with static methods to manage the end stage.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class EndStage {
    private static Stage endStage;
    private static final String FXML_PATH= "/com/example/sudoku/End-view.fxml";

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class.
     */
    private EndStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}

    /**
     * Sets the primary stage for the end view.
     *
     * @param stage the Stage to use for displaying the end screen
     */
    public static void setStage(Stage stage){endStage= stage;}

    /**
     * Loads and displays the end view.
     * Sets up the scene, title, and icon for the end stage.
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
        endStage.setScene(scene);
        endStage.setTitle("Final");
        endStage.getIcons().add(new Image(
                String.valueOf(MenuStage.class.getResource("/com/example/sudoku/Icons/taza.png"))
        ));
        endStage.show();
    }

    /**
     * Closes the end view/stage.
     */
    public static void deleteView() {
        endStage.close();
    }
}
