package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuStage  {
    private static Stage menuStage;
    private static final String FXML_PATH= "/com/example/sudoku/Menu-view.fxml";
    private MenuStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}
    public static void setStage(Stage stage){
        menuStage=stage;
    }
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

    public static void deleteView() {
        menuStage.close();
    }
}
