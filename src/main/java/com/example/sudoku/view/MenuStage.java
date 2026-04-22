package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuStage  {
    private static final String FXML_PATH= "/com/example/sudoku/Menu-view.fxml";
    private MenuStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}

    public static void showView(Stage stage) {
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
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.getIcons().add(new Image(
            String.valueOf(MenuStage.class.getResource("/com/example/sudoku/Icons/sudoku.png"))
        ));
        stage.show();
    }

    public static void deleteView(Stage stage) {
        stage.getScene().getWindow();
        stage.close();
    }
}
