package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the menu stage/window for the Sudoku application.
 * Handles loading and displaying the main menu from FXML.
 * This is a utility class with static methods to manage the menu stage.
 *
 * @author Sudoku Team
 * @version 1.0
 */
public class MenuStage {
    private static Stage menuStage;
    private static final String FXML_PATH= "/com/example/sudoku/Menu-view.fxml";

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class.
     */
    private MenuStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}

    /**
     * Sets the primary stage for the menu view.
     *
     * @param stage the Stage to use for displaying the menu
     */
    public static void setStage(Stage stage){
        menuStage=stage;
    }

    /**
     * Loads and displays the menu view.
     * Sets up the scene, title, and icon for the menu stage.
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
        menuStage.setScene(scene);
        menuStage.setTitle("Menu");
        menuStage.getIcons().add(new Image(
            String.valueOf(MenuStage.class.getResource("/com/example/sudoku/Icons/sudoku.png"))
        ));
        menuStage.show();
    }

    /**
     * Closes the menu view/stage.
     */
    public static void deleteView() {
        menuStage.close();
    }
}
