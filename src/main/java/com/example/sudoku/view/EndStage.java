package com.example.sudoku.view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EndStage {
    private static Stage endStage;
    private static final String FXML_PATH= "/com/example/sudoku/End-view.fxml";
    private EndStage(){throw new UnsupportedOperationException("GameStage is a utility class.");}
    public static void setStage(Stage stage){endStage= stage;}
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

    public static void deleteView() {
        endStage.close();
    }
}
