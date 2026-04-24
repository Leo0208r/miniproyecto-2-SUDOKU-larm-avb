package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage {
    private static Stage gameStage;
    private static final String FXML_PATH= "/com/example/sudoku/Game-view.fxml";
    private GameStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}
    public static void setStage(Stage stage){gameStage=stage;}
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

    public static void deleteView() {
        gameStage.close();
    }
}
