/**
 * Sudoku Game Application Module.
 * This module provides a complete Sudoku game implementation using JavaFX.
 * It includes game logic, UI controllers, models, and views for a 6x6 Sudoku puzzle.
 *
 * The module declares dependencies on javafx.controls and javafx.fxml for the GUI framework,
 * and exports all packages for external access. It opens all packages to JavaFX
 * for FXML loading and dependency injection.
 *
 * @author Sudoku Team
 * @version 1.0
 */
module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;

    opens com.example.sudoku to javafx.fxml;
    opens com.example.sudoku.controller to javafx.fxml;
    opens com.example.sudoku.model to javafx.fxml;
    opens com.example.sudoku.view to javafx.fxml;

    exports com.example.sudoku;
    exports com.example.sudoku.controller;
    exports com.example.sudoku.model;
    exports com.example.sudoku.view;
}