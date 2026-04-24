package com.example.sudoku.controller;

import com.example.sudoku.view.EndStage;
import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.MenuStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {
    @FXML
    public void onHandleStart(ActionEvent event){
        MenuStage.deleteView();
        GameStage.showView();
    }
}
