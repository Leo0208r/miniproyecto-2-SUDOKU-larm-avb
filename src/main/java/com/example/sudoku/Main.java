package com.example.sudoku;

import com.example.sudoku.view.MenuStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MenuStage.showView(primaryStage);
    }
}